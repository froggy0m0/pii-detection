package com.froggy.piidetection.common;

import com.froggy.piidetection.common.dto.DetectionDto;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DetectorRegistry {

    private static final DetectorRegistry detectorRegistry = new DetectorRegistry();
    private static final List<Detector<?>> detectors = new ArrayList<>();

    public static DetectorRegistry getInstance() {
        return detectorRegistry;
    }

    static {
        // 인스턴스 최초 호출시점에 초기화진행
        detectorRegistry.initDetectors();
    }

    public List<DetectionDto> execute(String inputText) {
        List<DetectionDto> results = new ArrayList<>();
        for (Detector<?> detector : detectors) {
            results.add(detector.detect(inputText));
        }

        return results;
    }

    /**
     * Detector 인터페이스를 구현한 클래스를 찾아
     * 클리스변수 'detectors' 리스트에 등록하는 메소드
     */
    private void initDetectors() {
        String packageName = "com.froggy.piidetection";
        String packagePath = packageName.replace('.', '/');
        try {

            URL resource = getClass().getClassLoader().getResource(packagePath);
            if (resource == null) {
                throw new IllegalStateException("Package " + packageName + " not found");
            }

            File rootDirectory  = new File(resource.toURI());
            if (rootDirectory.exists() && rootDirectory.isDirectory()) {
                scanAndRegisterDetectors(rootDirectory, packageName);
            }

        } catch (URISyntaxException e) {
            throw new RuntimeException("Error accessing package path: " + packagePath, e);
        }
    }

    /**
     * File 에서 Detector 인터페이스를 구현한 클래스를 찾기위한 메소드
     * 패키지 내에 하위 패키지를 탐색하기 위해 재귀함수로 구현
     */
    private void scanAndRegisterDetectors(File directory, String packageName) {
        for (File file : directory.listFiles()) {
            if (file.isDirectory()) {
                scanAndRegisterDetectors(file, packageName + "." + file.getName());
            }

            if (file.getName().endsWith(".class")) {
                try {
                    String className = packageName + "." + file.getName().replace(".class", "");
                    Class<?> clazz = Class.forName(className);

                    // Detector 인터페이스를 구현한 클래스인지 확인
                    if (Detector.class.isAssignableFrom(clazz) && !Modifier.isAbstract(clazz.getModifiers())) {
                        registerDetector((Detector<?>) clazz.getDeclaredConstructor().newInstance());
                    }

                } catch (ClassNotFoundException e) {
                    throw new RuntimeException("Failed to load class" + e);
                } catch (InvocationTargetException | InstantiationException |
                         IllegalAccessException | NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private void registerDetector(Detector<?> detector) {
        detectors.add(detector);
    }

}

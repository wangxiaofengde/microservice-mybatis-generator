package org.mybatis.generator.ui.model;

public class GeneratorConfig {
    /**
     * 配置名称
     */
    private String name;

    private String tableName;
    private boolean sharding;
    private String deleteTablePre;
    private String projectFolder;

    private boolean buildModel;
    private boolean overrideModel;
    private String modelPackage;
    private String modelFolder;

    private boolean buildExample;
    private boolean overrideExample;
    private String examplePackage;
    private String exampleFolder;

    private boolean buildMapper;
    private boolean overrideMapper;
    private String mapperPackage;
    private String mapperFolder;

    private boolean buildXML;
    private boolean overrideXML;
    private String xmlPackage;
    private String xmlFolder;

    private boolean buildService;
    private boolean overrideService;
    private String servicePackage;
    private String serviceFolder;

    private boolean buildServiceImpl;
    private boolean overrideServiceImpl;
    private String serviceImplPackage;
    private String serviceImplFolder;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTableName() {
        return this.tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public boolean isSharding() {
        return this.sharding;
    }

    public void setSharding(boolean sharding) {
        this.sharding = sharding;
    }

    public String getDeleteTablePre() {
        return this.deleteTablePre;
    }

    public void setDeleteTablePre(String deleteTablePre) {
        this.deleteTablePre = deleteTablePre;
    }

    public String getProjectFolder() {
        return this.projectFolder;
    }

    public void setProjectFolder(String projectFolder) {
        this.projectFolder = projectFolder;
    }

    public boolean isBuildModel() {
        return this.buildModel;
    }

    public void setBuildModel(boolean buildModel) {
        this.buildModel = buildModel;
    }

    public boolean isOverrideModel() {
        return this.overrideModel;
    }

    public void setOverrideModel(boolean overrideModel) {
        this.overrideModel = overrideModel;
    }

    public String getModelPackage() {
        return this.modelPackage;
    }

    public void setModelPackage(String modelPackage) {
        this.modelPackage = modelPackage;
    }

    public String getModelFolder() {
        return this.modelFolder;
    }

    public void setModelFolder(String modelFolder) {
        this.modelFolder = modelFolder;
    }

    public boolean isBuildExample() {
        return this.buildExample;
    }

    public void setBuildExample(boolean buildExample) {
        this.buildExample = buildExample;
    }

    public boolean isOverrideExample() {
        return this.overrideExample;
    }

    public void setOverrideExample(boolean overrideExample) {
        this.overrideExample = overrideExample;
    }

    public String getExamplePackage() {
        return this.examplePackage;
    }

    public void setExamplePackage(String examplePackage) {
        this.examplePackage = examplePackage;
    }

    public String getExampleFolder() {
        return this.exampleFolder;
    }

    public void setExampleFolder(String exampleFolder) {
        this.exampleFolder = exampleFolder;
    }

    public boolean isBuildMapper() {
        return this.buildMapper;
    }

    public void setBuildMapper(boolean buildMapper) {
        this.buildMapper = buildMapper;
    }

    public boolean isOverrideMapper() {
        return this.overrideMapper;
    }

    public void setOverrideMapper(boolean overrideMapper) {
        this.overrideMapper = overrideMapper;
    }

    public String getMapperPackage() {
        return this.mapperPackage;
    }

    public void setMapperPackage(String mapperPackage) {
        this.mapperPackage = mapperPackage;
    }

    public String getMapperFolder() {
        return this.mapperFolder;
    }

    public void setMapperFolder(String mapperFolder) {
        this.mapperFolder = mapperFolder;
    }

    public boolean isBuildXML() {
        return this.buildXML;
    }

    public void setBuildXML(boolean buildXML) {
        this.buildXML = buildXML;
    }

    public boolean isOverrideXML() {
        return this.overrideXML;
    }

    public void setOverrideXML(boolean overrideXML) {
        this.overrideXML = overrideXML;
    }

    public String getXmlPackage() {
        return this.xmlPackage;
    }

    public void setXmlPackage(String xmlPackage) {
        this.xmlPackage = xmlPackage;
    }

    public String getXmlFolder() {
        return this.xmlFolder;
    }

    public void setXmlFolder(String xmlFolder) {
        this.xmlFolder = xmlFolder;
    }

    public boolean isBuildService() {
        return this.buildService;
    }

    public void setBuildService(boolean buildService) {
        this.buildService = buildService;
    }

    public boolean isOverrideService() {
        return this.overrideService;
    }

    public void setOverrideService(boolean overrideService) {
        this.overrideService = overrideService;
    }

    public String getServicePackage() {
        return this.servicePackage;
    }

    public void setServicePackage(String servicePackage) {
        this.servicePackage = servicePackage;
    }

    public String getServiceFolder() {
        return this.serviceFolder;
    }

    public void setServiceFolder(String serviceFolder) {
        this.serviceFolder = serviceFolder;
    }

    public boolean isBuildServiceImpl() {
        return this.buildServiceImpl;
    }

    public void setBuildServiceImpl(boolean buildServiceImpl) {
        this.buildServiceImpl = buildServiceImpl;
    }

    public boolean isOverrideServiceImpl() {
        return this.overrideServiceImpl;
    }

    public void setOverrideServiceImpl(boolean overrideServiceImpl) {
        this.overrideServiceImpl = overrideServiceImpl;
    }

    public String getServiceImplPackage() {
        return this.serviceImplPackage;
    }

    public void setServiceImplPackage(String serviceImplPackage) {
        this.serviceImplPackage = serviceImplPackage;
    }

    public String getServiceImplFolder() {
        return this.serviceImplFolder;
    }

    public void setServiceImplFolder(String serviceImplFolder) {
        this.serviceImplFolder = serviceImplFolder;
    }

    @Override
    public String toString() {
        return "GeneratorConfig [name=" + this.name + ", tableName=" + this.tableName + ", sharding="
                + this.sharding + ", deleteTablePre=" + this.deleteTablePre + ", projectFolder="
                + this.projectFolder + ", buildModel=" + this.buildModel + ", overrideModel="
                + this.overrideModel + ", modelPackage=" + this.modelPackage + ", modelFolder="
                + this.modelFolder + ", buildExample=" + this.buildExample + ", overrideExample="
                + this.overrideExample + ", examplePackage=" + this.examplePackage + ", exampleFolder="
                + this.exampleFolder + ", buildMapper=" + this.buildMapper + ", overrideMapper="
                + this.overrideMapper + ", mapperPackage=" + this.mapperPackage + ", mapperFolder="
                + this.mapperFolder + ", buildXML=" + this.buildXML + ", overrideXML=" + this.overrideXML
                + ", xmlPackage=" + this.xmlPackage + ", xmlFolder=" + this.xmlFolder + ", buildService="
                + this.buildService + ", overrideService=" + this.overrideService + ", servicePackage="
                + this.servicePackage + ", serviceFolder=" + this.serviceFolder + ", buildServiceImpl="
                + this.buildServiceImpl + ", overrideServiceImpl=" + this.overrideServiceImpl
                + ", serviceImplPackage=" + this.serviceImplPackage + ", serviceImplFolder="
                + this.serviceImplFolder + "]";
    }
}

package com.shuframework.auto;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.shuframework.commonbase.util.SystemUtil;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * <p>
 * 代码生成器演示
 * </p>
 *
 * @author shu
 */
public class MybatisGenerator {

    /**
     * <p>
     * MySQL 生成演示
     * 建议一个模块一个模块的生成，pc.setParent 这个是根路径，然后设置需要生成的表名
     * </p>
     */
    public static void main(String[] args) {
        String author = "shuheng";
        String moduleName = "admin";
        String[] tables = {"sys_user"};
        generatorJava(author, moduleName, tables);
    }

    private static void generatorJava(String author, String moduleName, String... tables) {
        /* 获取 JDBC 配置文件 */
        Properties props = getProperties();
        AutoGenerator mpg = new AutoGenerator();

        String outputDir = SystemUtil.USER_DIR + "/" + "src/main/java" + "/";
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(outputDir);
        gc.setFileOverride(true);
        gc.setActiveRecord(false);// 关闭activeRecord 模式
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(true);// XML columList
        gc.setAuthor(author);

        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setControllerName("%sController");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setTypeConvert(new MySqlTypeConvert());
        dsc.setDriverName(props.getProperty("jdbc.driverClassName"));
        dsc.setUrl(props.getProperty("jdbc.url"));
        dsc.setUsername(props.getProperty("jdbc.username"));
        dsc.setPassword(props.getProperty("jdbc.password"));
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(moduleName);  //所属模块
        pc.setParent("com.shuframework"); // 自定义包路径
        pc.setController("controller"); // 这里是控制器包名，默认 web
        pc.setEntity("model");
        pc.setXml("sqlMapperXml");
        mpg.setPackageInfo(pc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setEntityLombokModel(true);//开启lombok
        strategy.setRestControllerStyle(true);//开启rest
//         strategy.setCapitalMode(true);// 全局大写命名
//         strategy.setDbColumnUnderline(true);//全局下划线命名
//		strategy.setTablePrefix(new String[] { "bmd_", "mp_" });// 此处可以修改为您的表前缀
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
        strategy.setInclude(tables); // 需要生成的表
        // strategy.setExclude(new String[]{"test"}); // 排除生成的表
        // 自定义实体父类
        // strategy.setSuperEntityClass("com.baomidou.demo.TestEntity");
        // 自定义实体，公共字段
        // strategy.setSuperEntityColumns(new String[] { "test_id", "age" });
        // 自定义 mapper 父类
        // strategy.setSuperMapperClass("com.baomidou.demo.TestMapper");
        // 自定义 service 父类
        // strategy.setSuperServiceClass("com.baomidou.demo.TestService");
        // 自定义 service 实现类父类
        // strategy.setSuperServiceImplClass("com.baomidou.demo.TestServiceImpl");
        // 自定义 controller 父类
//		strategy.setSuperControllerClass("com.wangzhixuan.commons.base.BaseController");
        mpg.setStrategy(strategy);

        // 自定义模板配置，可以 copy 源码 mybatis-plus/src/main/resources/templates 下面内容修改，
        // 放置自己项目的 src/main/resources/templates 目录下, 默认名称一下可以不配置，也可以自定义模板名称
        TemplateConfig tc = new TemplateConfig();
        tc.setController("/templates/restcontroller.java.vm");
//        tc.setController("/templates/controller.java.vm");
        tc.setService("/templates/service.java.vm");
        tc.setServiceImpl("/templates/serviceImpl.java.vm");
        tc.setEntity("/templates/entity.java.vm");
        tc.setMapper("/templates/mapper.java.vm");
        tc.setXml("/templates/mapper.xml.vm");
        // 如上任何一个模块如果设置 空 OR Null 将不生成该模块。
        mpg.setTemplate(tc);

        //生成自定义页面
        //链接地址： https://blog.csdn.net/qq_33842795/article/details/80227382
        InjectionConfig cfg = initCustomPage(outputDir, pc,false);
        mpg.setCfg(cfg);
        // 执行生成
        mpg.execute();
    }

    /**
     * 获取配置文件
     *
     * @return 配置Props
     */
    private static Properties getProperties() {
        // 读取配置文件
        Resource resource = new ClassPathResource("/config/db.properties");
        Properties props = new Properties();
        try {
            props = PropertiesLoaderUtils.loadProperties(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }


    //生成自定义页面
    private static InjectionConfig initCustomPage(String outputDir, PackageConfig pc, boolean isNeedFrontPage) {
        // 注入自定义配置，可以在 VM 中使用 cfg.abc 设置的值
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {}
        };
        List<FileOutConfig> focList = new ArrayList<>();
        //添加前端页面部分
        if(isNeedFrontPage) {
            addFrontCustomePage(outputDir, pc.getModuleName(), focList);
        }else{
            addJavaCustomPage(outputDir, pc, focList);
        }

        cfg.setFileOutConfigList(focList);
        return cfg;
    }

    private static void addJavaCustomPage(String outputDir, PackageConfig pc, List<FileOutConfig> focList) {
//        String packageName = pc.getParent() + pc.getModuleName();
        //pc.getParent() 底层的实现是已经拼接了moduleName
        String packagePath = pc.getParent().replace(".","/");
        final String viewOutputDir = outputDir + packagePath + "/query/";
        // 生成的模版路径，不存在时需要先新建
        File viewDir = new File(viewOutputDir);
        if (!viewDir.exists()) {
            viewDir.mkdirs();
        }

        addJava(focList, viewOutputDir, "/templates/querydto.java.vm");
    }

    private static void addJava(List<FileOutConfig> focList, String viewOutputDir, String templatePath) {
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
//                String name = StringUtils.firstToLowerCase(tableInfo.getEntityName());
                String path = viewOutputDir + File.separator + tableInfo.getEntityName() + "DTO.java";
                File viewDir = new File(path).getParentFile();
                if (!viewDir.exists()) {
                    viewDir.mkdirs();
                }
                return path;
            }
        });
    }

    private static void addFrontCustomePage(String outputDir, String moduleName, List<FileOutConfig> focList) {
        final String viewOutputDir = outputDir + "/view/";
        // 生成的模版路径，不存在时需要先新建
        File viewDir = new File(viewOutputDir);
        if (!viewDir.exists()) {
            viewDir.mkdirs();
        }

        addFront(focList, viewOutputDir, moduleName, "/templates/add.jsp.vm", "Add.jsp");
        addFront(focList, viewOutputDir, moduleName, "/templates/edit.jsp.vm", "Edit.jsp");
        addFront(focList, viewOutputDir, moduleName, "/templates/list.jsp.vm", "List.jsp");
    }

    private static void addFront(List<FileOutConfig> focList, String viewOutputDir, String moduleName, String templatePath, String suffixPath) {
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                String name = StringUtils.firstToLowerCase(tableInfo.getEntityName());
                String path = viewOutputDir + File.separator + moduleName + File.separator + name + suffixPath;
                File viewDir = new File(path).getParentFile();
                if (!viewDir.exists()) {
                    viewDir.mkdirs();
                }
                return path;
            }
        });
    }

}
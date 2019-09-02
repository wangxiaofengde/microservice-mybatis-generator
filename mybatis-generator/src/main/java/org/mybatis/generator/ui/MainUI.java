package org.mybatis.generator.ui;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.mybatis.generator.ui.controller.MainUIController;
import org.mybatis.generator.ui.util.ConfigHelper;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * 这是本软件的主入口,不用传入参数
 */
@SuppressWarnings("restriction")
public class MainUI extends Application {

    public static void main(String[] args) {
        String version = System.getProperty("java.version");
        if (Integer.parseInt(version.substring(0, 1)) == 1
                && Integer.parseInt(version.substring(2, 3)) >= 8
                && Integer.parseInt(version.substring(6)) >= 60
                || Integer.parseInt(version.substring(0, 1)) >= 9) {
            launch(args);
        } else {
            JFrame jFrame = new JFrame("版本错误");
            jFrame.setSize(500, 100);
            jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JPanel jPanel = new JPanel();
            JLabel jLabel = new JLabel("JDK的版本不能低于1.8.0.60，请升级至最近的JDK 1.8再运行此软件，当前版本:" + version);
            jPanel.add(jLabel);
            jFrame.add(jPanel);
            jFrame.setLocationRelativeTo(null);
            jFrame.setVisible(true);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ConfigHelper.init();

        URL url = Thread.currentThread().getContextClassLoader().getResource("fxml/MainUI.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        Parent root = fxmlLoader.load();
        primaryStage.setResizable(true);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        MainUIController controller = fxmlLoader.getController();
        controller.setPrimaryStage(primaryStage);
        AppProperties appPro = new AppProperties();
        appPro.init();
    }
}

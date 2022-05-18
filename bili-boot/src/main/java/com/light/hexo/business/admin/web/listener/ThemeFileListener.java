package com.light.hexo.business.admin.web.listener;

import com.light.hexo.business.admin.service.ThemeService;
import com.light.hexo.business.admin.web.listener.file.FileListenerFactory;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @ClassName: ThemeFileListener
 * @ProjectName: hexo-boot
 * @Description: 启动文件监听器
 */
@Component
public class ThemeFileListener implements CommandLineRunner {

    @Autowired
    private ThemeService themeService;

    @Autowired
    private FileListenerFactory fileListenerFactory;

    @Override
    public void run(String... args) throws Exception {

        try {
            // 检测主题目录
            this.themeService.checkThemeByStartup();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            // 启动线程，监听文件变化
            FileAlterationMonitor fileAlterationMonitor = fileListenerFactory.createMonitor();
            fileAlterationMonitor.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

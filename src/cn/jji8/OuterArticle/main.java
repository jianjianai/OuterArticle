package cn.jji8.OuterArticle;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class main extends JavaPlugin implements Listener {
    public void onEnable(){
        getLogger().info("开始加载..");
        Bukkit.getPluginManager().registerEvents(this,this);
        if(!getConfig().contains("//")){
            getConfig().set("//","不执命令行可以填 \"-\" 玩家变量是 %玩家% ");
            try {
                getConfig().save(new File(getDataFolder(),"Config.yml"));
            } catch (IOException e) {
                e.printStackTrace();
                getLogger().warning("配置文件生成失败");
            }
        }
        if(!getConfig().contains("点击时后台命令")){
            getConfig().set("点击时后台命令","say %玩家%");
            try {
                getConfig().save(new File(getDataFolder(),"Config.yml"));
            } catch (IOException e) {
                e.printStackTrace();
                getLogger().warning("配置文件生成失败");
            }
        }
        if(!getConfig().contains("点击时玩家命令")){
            getConfig().set("点击时玩家命令","say %玩家%");
            try {
                getConfig().save(new File(getDataFolder(),"Config.yml"));
            } catch (IOException e) {
                e.printStackTrace();
                getLogger().warning("配置文件生成失败");
            }
        }
        getLogger().info("加载完成");
    }
    /*
    * 玩家点击物品栏时触发的事件
    * */
    @EventHandler
    public void wanjiadianji(InventoryClickEvent a){
        if(a.getRawSlot()==-999){
            if(a.getCursor().getType().equals(Material.AIR)){
                if(!getConfig().getString("点击时后台命令").equals("-")){
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(),getConfig().getString("点击时后台命令").replaceAll("%玩家%",a.getWhoClicked().getName()));
                }
               if(!getConfig().getString("点击时玩家命令").equals("-")){
                   org.bukkit.Bukkit.getPlayer(a.getWhoClicked().getName()).performCommand(getConfig().getString("点击时玩家命令").replaceAll("%玩家%",a.getWhoClicked().getName()));
               }
            }
        }
    }
}

package cn.jji8.OuterArticle;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class main extends JavaPlugin implements Listener {
    config config;
    public void onEnable(){
        getLogger().info("开始加载..");
        saveDefaultConfig();
        config = new config(this);
        Bukkit.getPluginManager().registerEvents(this,this);
        getLogger().info("加载完成");
    }
    /*
    * 玩家点击物品栏时触发的事件
    * */
    @EventHandler(priority=EventPriority.MONITOR)
    public void wanjiadianji(InventoryClickEvent a){
        System.out.println("--------------------------------------------");
        if(a.isCancelled()){
            System.out.println("其他插件取消了本事件");
        }
        System.out.println("点击的格子序号是:"+a.getRawSlot());
        System.out.println("左键:"+a.isLeftClick());
        System.out.println("右键:"+a.isRightClick());
        System.out.println("shift:"+a.isShiftClick());
        System.out.println("鼠标物品:"+a.getCursor());
        System.out.println("点击物品:"+a.getCurrentItem());
        System.out.println("格子类型:"+a.getSlotType());
        System.out.println("Action类型:"+a.getAction());
        System.out.println("Click类型:"+a.getClick());
        System.out.println("--------------------------------------------");



        if(a.getRawSlot()!=-999){
            return;
        }
        if(!a.getCursor().getType().equals(Material.AIR)){
            return;
        }



        if(!getConfig().getString("点击时后台命令").equals("-")){
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),getConfig().getString("点击时后台命令").replaceAll("%玩家%",a.getWhoClicked().getName()));
        }
        if(!getConfig().getString("点击时玩家命令").equals("-")){
            org.bukkit.Bukkit.getPlayer(a.getWhoClicked().getName()).performCommand(getConfig().getString("点击时玩家命令").replaceAll("%玩家%",a.getWhoClicked().getName()));
        }
    }
}

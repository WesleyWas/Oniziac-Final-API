package com.weswaas.api.utils;

import org.bukkit.ChatColor;

/**
 * Created by Weswas on 28/04/2020.
 */
public class MessageUtils {

    public static final String HEART_WITH_COLOR = ChatColor.DARK_RED + "\u2764";

    public static double getHearts(double damaged, double finalDamage){

        return Math.ceil(damaged - finalDamage) / 2D;

    }

}

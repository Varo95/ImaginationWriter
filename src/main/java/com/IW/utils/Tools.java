package com.IW.utils;

import com.IW.App;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.MDL2IconFont;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.regex.Pattern;

public class Tools {

    private static final Logger logger = LoggerFactory.getLogger(Tools.class);
    private static final String URL_IMG_EXPRESSION = "(http)?s?:?(\\/\\/[^\"']*\\.(?:bmp|gif|jpg|jpeg|png))";

    /**
     * This method is used to set the icon and css
     *
     * @param window window to need apply styles
     */
    public static void addCssAndIcon(Stage window) {
        window.getScene().getStylesheets().add(String.valueOf(App.class.getResource("dark.css")));
        window.getIcons().add(getImage("icon.png", true));
    }

    /**
     * This method is used to validate an url from internet
     *
     * @param url url to check
     * @return true if ends with jpg, gif, or png, otherwhise, false
     */
    public static boolean Validate_img_URL(String url) {
        return Pattern.compile(URL_IMG_EXPRESSION).matcher(url).matches();
    }

    /**
     * This method is used to validate an url from internet
     *
     * @param url url to check
     * @return true if ends with jpg, gif, or png, otherwhise, false
     */
    public static boolean ValidateFile_img(String url) {
        boolean result = switch (url.toLowerCase().substring(url.length() - 4, url.length())) {
            case ".bmp", ".gif", ".jpg", ".png" -> true;
            default -> false;
        };
        if (!result) {
            if (url.endsWith(".jpeg")) {
                result = true;
            }
        }
        return result;
    }

    /**
     * This util is used to get an Image
     *
     * @param resPath   relative or absolute path folder, like "foo/bar.png" or "/home/user/foo.png" or "C:\Users\User\Desktop\bar.png"
     * @param isResPath indicate if the source is on resPath(jar) or not
     * @return the Image from the resources loaded
     */
    public static Image getImage(String resPath, boolean isResPath) {
        if (isResPath)
            return new Image(Objects.requireNonNull(App.class.getResourceAsStream(resPath)));
        else {
            if (!Validate_img_URL(resPath)) {
                File f = new File(resPath);
                if (f.exists() && f.isFile())
                    if (ValidateFile_img(f.getName()))
                        return new Image(Objects.requireNonNull(f.getAbsoluteFile().getAbsolutePath()));
            } else if (Validate_img_URL(resPath)) {
                return new Image(resPath);
            }
            return null;
        }
    }

    /**
     * This method is used to encrypt Strings with MD5 hash
     *
     * @param s String that needs to hash
     * @return hashed MD5 string
     */
    public static String encryptSHA256(String s) {
        String result = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA256");
            md.update(s.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte aByte : md.digest()) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            result = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            logger.error("no Provider supports a MessageDigestSpi implementation for the specified algorithm" + e.getMessage());
        }
        return result;
    }

    /**
     * This method is used to get icons and put them into a window, making less code and more readable to coders
     * Codes are extracted from Microsoft official page -> https://docs.microsoft.com/en-us/windows/apps/design/style/segoe-ui-symbol-font
     */
    //Para añadir nuevos iconos consultar la página de Microsoft-> https://docs.microsoft.com/en-us/windows/apps/design/style/segoe-ui-symbol-font
    //Añadir "\\u" al principio (al igual que el \n imprime salto de línea) para indicar que es un icono gráfico de Windows.
    public static MDL2IconFont getIcon(String value) {
        MDL2IconFont result = switch (value) {
            case "close" -> new MDL2IconFont("\uE711");
            case "cloud" -> {
                result = new MDL2IconFont("\uE753");
                result.setStyle("-fx-text-fill: #00a8a0;");
                yield result;
            }
            case "info" -> {
                result = new MDL2IconFont("\uE946");
                result.setStyle("-fx-text-fill: lightblue;");
                yield result;
            }
            case "arrow-next" -> {
                result = new MDL2IconFont("\uF0D2");
                result.setStyle("-fx-text-fill: lightblue;");
                yield result;
            }
            case "arrow-back" -> {
                result = new MDL2IconFont("\uF0D3");
                result.setStyle("-fx-text-fill: lightblue;");
                yield result;
            }
            case "add" -> {
                result = new MDL2IconFont("\uECC8");
                result.setStyle("-fx-text-fill: lightgreen;");
                yield result;
            }
            case "remove" -> {
                result = new MDL2IconFont("\uECC9");
                result.setStyle("-fx-text-fill: red;");
                yield result;
            }
            case "edit" -> {
                result = new MDL2IconFont("\uEC87");
                result.setStyle("-fx-text-fill: orange;");
                yield result;
            }
            case "close-session"-> {
                result = new MDL2IconFont("\uF3B1");
                yield result;
            }
            case "book_chars" -> {
                result = new MDL2IconFont("\uE726");
                yield result;
            }
            case "book_scenes" -> {
                result = new MDL2IconFont("\uE91B");
                yield result;
            }
            default -> new MDL2IconFont("\uF16B");
        };
        return result;
    }
}

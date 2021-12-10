package GUI.model;

import BE.Theme;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class ThemeModel {
    List<Theme> listThemes;
    public ThemeModel() {

        //This Model and the class & methods asscociated is a proof of concept,
        //the them could be added in a database (or configuration files)
        //and with a combination of css and images, it will be possible to change dramatically the look & feel
        //of the Mp3 reader => like a player of old....
        listThemes = new ArrayList<>();
        Theme darkModel = new Theme("DarkTheme", "/css/DarkTheme/DarkTheme.css");
        Theme lightModel = new Theme("LightTheme","/css/LightTheme/LightTheme.css");
        Theme defaultModel = new Theme("DefaultTheme", "/css/DefaultTheme/DefaultTheme.css");
        listThemes.add(defaultModel);
        listThemes.add(darkModel);
        listThemes.add(lightModel);

    }

    public ObservableList<Theme> getListThemes() {
        ObservableList<Theme> listObsThemes = FXCollections.observableArrayList();
        listObsThemes.addAll(listThemes);
        return listObsThemes;
    }
}

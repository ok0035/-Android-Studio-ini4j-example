package com.initest.myapplication;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.ini4j.Ini;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private Ini iniFile;
    private String iniStr;
    private Ini.Section iniSection;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadIni();

        iniSection = iniFile.get("Section"); // 섹션 이름을 입력
        iniStr = iniSection.get("test"); // 키값을 이용하여 값을 가져옴
        Log.d(iniSection.getName(), iniStr);

        iniSection = iniFile.get("Section2"); // 섹션 변경
        iniStr = iniSection.get("test");
        Log.d(iniSection.getName(), iniStr);

    }

    private boolean loadIni() {
        AssetManager assetMgr = getResources().getAssets();
        InputStream inputStream = null;

        try {
            inputStream = assetMgr.open("test.ini"); // assets 폴더에 있는 test.ini를 open
            iniFile = new Ini(inputStream); // ini4j 라이브러리에서 test.ini를 읽음
        } catch (IOException e) {
            e.printStackTrace();
            return false; // 실패하면 false 반환
        }
        return true; // 성공하면 true 반환
    }

}

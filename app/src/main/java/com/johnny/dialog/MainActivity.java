package com.johnny.dialog;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 通知对话框
     *
     * @param v
     */
    public void showNotifyDialog(View v) {
        /*初始化dialog构造器*/
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("通知对话框:");/*设置dialog的title*/
        builder.setMessage("通知对话框弹出了");/*设置dialog的内容*/
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {/*设置dialog确认按钮的点击事件*/
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "OK", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {/*设置dialog取消按钮的点击事件*/
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "CANCEL", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setCancelable(false);/*设置是否可以通过返回键消失, 默认true*/
        builder.show();/*显示dialog*/
        /* 第二种显示方法
        * AlertDialog dialog = builder.create();
        * dialog.show();
        */
    }

    /**
     * 显示列表对话框
     *
     * @param v
     */
    public void showListDialog(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("请选中您喜欢的数字:");
        builder.setIcon(R.mipmap.ic_launcher);/*设置图标*/
        final String[] items = new String[]{"8", "6", "2", "1", "3"};/*设置列表的内容*/
        builder.setItems(items, new DialogInterface.OnClickListener() {/*设置列表的点击事件*/
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, items[which], Toast.LENGTH_SHORT).show();
            }
        });
        builder.setCancelable(false);
        builder.show();

    }

    /**
     * 显示单选对话框
     *
     * @param v
     */
    public void showSinpleChioceDialog(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("选择性别:");
        builder.setIcon(R.mipmap.ic_launcher);
        final String[] items = new String[]{"男", "女", "妖"};
        builder.setSingleChoiceItems(items, 2, new DialogInterface.OnClickListener() {/*设置单选条件的点击事件*/
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, items[which], Toast.LENGTH_SHORT).show();
            }
        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "OK", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "CANCEL", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setCancelable(false);
        builder.show();
    }

    /**
     * 显示多选对话框
     *
     * @param v
     */
    public void showMultiChioceDialog(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("于谦:");
        builder.setIcon(R.mipmap.ic_launcher);
        final String[] items = new String[]{"抽烟", "喝酒", "烫头"};/*设置多选的内容*/
        final boolean[] checkedItems = new boolean[]{false, true, false};/*设置多选默认状态*/
        builder.setMultiChoiceItems(items, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {/*设置多选的点击事件*/
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                checkedItems[which] = isChecked;
                Toast.makeText(MainActivity.this, items[which] + " 状态: " + isChecked, Toast.LENGTH_SHORT).show();
            }
        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "OK", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "CANCEL", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setCancelable(false);
        builder.show();
    }

    /**
     * 显示进度对话框
     *
     * @param v
     */
    public void showProgressDialog(View v) {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle("提醒");/*设置进度对话框的title*/
        dialog.setMessage("正在加载中,请稍后...");/*设置进度对话框的内容*/
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);/*设置进度对话框的样式*/
        dialog.setMax(100);/*设置进度对话框的最大进度*/
        dialog.setCancelable(false);
        dialog.show();/*在show时, 别忘了初始化进度*/
        dialog.setProgress(23);/*设置进度*/
        new Thread() {
            public void run() {
                while (true) {
                    if (dialog.getProgress() == 100)
                        return;
                    SystemClock.sleep(100);
                    dialog.incrementProgressBy(1);/*更新进度,每一次以5来递增(dialog.setProgress(dialog.getProgress() + 5))*/
                    if (dialog.getProgress() >= dialog.getMax()) {
                        runOnUiThread(new Runnable() {/*在主线程执行*/
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, "加载完毕", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        });
                    }
                }
            }
        }.start();
    }
}

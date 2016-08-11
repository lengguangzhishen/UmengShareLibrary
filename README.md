# UmengShareLibrary
封装的友盟分享依赖包

调用:
public class MainActivity extends AppCompatActivity {

    private ShareDialog mShareDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initShareDialog();
    }

    private void initShareDialog() {
        mShareDialog = new ShareDialog.Build(this)
                .setTitle("title")
                .setContent("content")
                .setTargetUrl("http://www.baidu.com")
                .setImageUrl("http://www.umeng.com/images/pic/social/integrated_3.png")
                .build();
    }

    public void click(View view) {
        mShareDialog.showDialog();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mShareDialog.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mShareDialog.destory();
    }
}

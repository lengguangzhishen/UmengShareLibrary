# UmengShareLibrary
封装的友盟分享依赖包

初始化:

        ShareDialog.isDebug(true);
        ShareDialog.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
        ShareDialog.setSina("3921700954", "04b48b094faeb16683c32669824ebdad");
        ShareDialog.setQQ("100424468", "c7394704798a158208a74ab60104f0ba");

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


清单文件和混淆设置和友盟官网一样

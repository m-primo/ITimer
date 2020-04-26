package itimer.iapp.gp;

import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.content.*;
import android.graphics.*;
import android.media.*;
import android.net.*;
import android.text.*;
import android.util.*;
import android.webkit.*;
import java.util.*;
import java.text.*;


public class MainActivity extends Activity {

	private LinearLayout bg;
	private LinearLayout linear4;
	private LinearLayout topp;
	private LinearLayout content;
	private TextView textview1;
	private TextView textview2;
	private TextView time_view;
	private Button start;
	private LinearLayout linear1;
	private LinearLayout linear2;
	private LinearLayout linear3;
	private Button stop;
	private Button reset;
	private Button about_btn;
	private Button more_apps;
	private WebView webview1;

	private double tm = 0;
	private double h = 0;
	private double m = 0;
	private double s = 0;


	private Timer _timer = new Timer();
	private TimerTask timer;
	private Intent open = new Intent();
	private AlertDialog.Builder dialog;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initialize();
		initializeLogic();
	}

	private void  initialize() {
		bg = (LinearLayout) findViewById(R.id.bg);
		linear4 = (LinearLayout) findViewById(R.id.linear4);
		topp = (LinearLayout) findViewById(R.id.topp);
		content = (LinearLayout) findViewById(R.id.content);
		textview1 = (TextView) findViewById(R.id.textview1);
		textview2 = (TextView) findViewById(R.id.textview2);
		time_view = (TextView) findViewById(R.id.time_view);
		start = (Button) findViewById(R.id.start);
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		linear2 = (LinearLayout) findViewById(R.id.linear2);
		linear3 = (LinearLayout) findViewById(R.id.linear3);
		stop = (Button) findViewById(R.id.stop);
		reset = (Button) findViewById(R.id.reset);
		about_btn = (Button) findViewById(R.id.about_btn);
		more_apps = (Button) findViewById(R.id.more_apps);
		webview1 = (WebView) findViewById(R.id.webview1);
		webview1.getSettings().setJavaScriptEnabled(true);
		webview1.getSettings().setSupportZoom(true);
		webview1.setWebViewClient(new WebViewClient() {
				@Override
				public void onPageStarted(WebView _view, String _url, Bitmap _favicon) {

					super.onPageStarted(_view, _url, _favicon);
				}
				@Override
				public void onPageFinished(WebView _view, String _url) {

					super.onPageFinished(_view, _url);
				}
			});



		dialog = new AlertDialog.Builder(this);

		start.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _v) { 
					timer = new TimerTask() {
						@Override
						public void run() {
							runOnUiThread(new Runnable() {
									@Override
									public void run() {
										tm++;
										_SetTimerText(tm);
									}
								});
						}
					};
					_timer.scheduleAtFixedRate(timer, (int)(1), (int)(1000));
					showMessage("Timer Started");
				}
			});
		stop.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _v) { 
					timer.cancel();
					showMessage("Timer Stopped");
				}
			});
		reset.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _v) { 
					timer.cancel();
					_SetTimerText(0);
					tm = 0;
					h = 0;
					m = 0;
					s = 0;
					showMessage("Timer Reset");
				}
			});
		about_btn.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _v) { 
					dialog.setTitle("About");
					dialog.setMessage(" © ( IApp ) Powered by GP & PWS & Elgeneral Primo \n\n Site : PWS-Apps.BlogSpot.Com \n\n ITimer V 1.3");
					dialog.setNeutralButton("OK", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface _dialog, int _which) {

							}
						});
					dialog.create().show();
				}
			});
		more_apps.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _v) { 
					open.setAction(Intent.ACTION_VIEW);
					open.setData(Uri.parse("http://pws-apps.blogspot.com/search/?label=IApp"));
					startActivity(open);
				}
			});

	}

	private void  initializeLogic() {
		webview1.loadUrl("file:///android_asset/mob_ads.html");
		webview1.clearCache(true);
	}


	private void _SetTimerText (double _Secs) {
		h = _Secs / 3600;
		m = (_Secs % 3600) / 60;
		s = _Secs % 60;
		time_view.setText(String.valueOf((long)(h)).concat(" : ").concat(String.valueOf((long)(m)).concat(" : ").concat(String.valueOf((long)(s)))));
	}


	// created automatically
	private void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}

	private int getRandom(int _minValue ,int _maxValue){
		Random random = new Random();
		return random.nextInt(_maxValue - _minValue + 1) + _minValue;
	}

	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
				_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}

}


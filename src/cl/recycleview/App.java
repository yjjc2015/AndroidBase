package cl.recycleview;

import android.content.Intent;
import android.graphics.drawable.Drawable;

public class App {
	private String name;
	private Intent intent;
	private Drawable icon;
	public App(String name, Drawable icon, Intent intent) {
		this.name = name;
		this.icon = icon;
		this.intent = intent;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Intent getIntent() {
		return intent;
	}
	public void setIntent(Intent intent) {
		this.intent = intent;
	}
	public Drawable getIcon() {
		return icon;
	}
	public void setIcon(Drawable icon) {
		this.icon = icon;
	}
}

package cl.recycleview;

import java.util.ArrayList;
import java.util.List;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

public class AppHelper {
	public static List<App> getLauncherApps(PackageManager pm){
		List<App> list = new ArrayList<App>();
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_LAUNCHER);
		List<ResolveInfo> infos = pm.queryIntentActivities(intent, 0);
		App app = null;
		for(ResolveInfo info : infos){
			app = new App(info.loadLabel(pm).toString(), 
					info.activityInfo.loadIcon(pm),
//					info.loadIcon(pm),			
					new Intent().setComponent(new ComponentName(
							info.activityInfo.packageName, 
							info.activityInfo.name)));
			list.add(app);
		}
		return list;
	}
}

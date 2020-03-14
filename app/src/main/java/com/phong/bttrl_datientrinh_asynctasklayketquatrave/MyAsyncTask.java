package com.phong.bttrl_datientrinh_asynctasklayketquatrave;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * AsyncTask<Integer, Integer, ArrayList<Integer>>
 * Integer: Truyền vào lúc thực thi tiến trình
 * Integer: Giá trị để Cập nhật giao diện
 * ArrayList<Integer>: Kết quả trả về sau khi kết thúc tiến trình
 */
public class MyAsyncTask extends AsyncTask<Integer,Integer, ArrayList<Integer>> {
    Activity activityMain;
    TextView txtTotal;
    ListView lvFibocacci = null;
    ArrayList<Integer> arrayCuaListView = new ArrayList<Integer>();
    ArrayAdapter adapterCuaListView = null;
    public MyAsyncTask(Activity activity){
        //Lấy MainActivity:
        activityMain = activity;
        //Lấy TextView của MainActivity:
        txtTotal = activityMain.findViewById(R.id.txtTotal);
        //Lấy ListView của MainActivity:
        lvFibocacci = activityMain.findViewById(R.id.lvFibonacci);
        //Adapter cho ListView:
        adapterCuaListView = new ArrayAdapter(
                activityMain,
                android.R.layout.simple_list_item_1,
                arrayCuaListView);
        //Gán Adapter cho ListView:
        lvFibocacci.setAdapter(adapterCuaListView);
    }

    /**
     * Số fibonacci thứ n trong chuỗi Fibonacci
     * @param n
     * @return
     */
    public int fibonacci(int n){
        if (n <= 2){
            return 1;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    @Override
    protected ArrayList<Integer> doInBackground(Integer... integers) {
        //vậy thì khi bắt đầu thực thi đối số 1 sẽ ở đây: integers
        int so = integers[0];
        //Khai báo ArrayList lưu trữ tập các số nguyên:
        ArrayList<Integer> arrayListTongCacSoFib = new ArrayList<Integer>();
        for (int i = 1; i <= so; i++){
            SystemClock.sleep(150);
            //Lấy số fibonacci tại vị trí thứ i:
            int fibonacci = fibonacci(i);
            //Lưu vào danh sách:
            arrayListTongCacSoFib.add(fibonacci);
            //cập nhập số fibonacci lên listview:
            publishProgress(fibonacci);
        }
        //trả về danh sách, nó sẽ được lưu trữ trong hàm
        //onPostExecute
        return arrayListTongCacSoFib;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //muốn khởi tạo gì đó trước khi thực thi thì có thể làm ở đây
        Toast.makeText(activityMain,"Bắt đầu chạy tiến trình!",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPostExecute(ArrayList<Integer> integers) {
        super.onPostExecute(integers);
        //integers chính là ArrayList lưu trữ chuỗi Fibonacci
        int tong =0;
        //vòng lặp để cộng dồn lại:
        for (int v : integers){
            tong = tong + v;
        }
        Toast.makeText(activityMain,"Tổng = " + tong,Toast.LENGTH_LONG).show();
        //cập nhật lên TextView:
        txtTotal.setText(tong + "");
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        //Lấy giá trị truyền từ publishProgess(doInBackground):
        arrayCuaListView.add(values[0]);
        //cập nhật lại giao diện:
        adapterCuaListView.notifyDataSetChanged();
    }
}

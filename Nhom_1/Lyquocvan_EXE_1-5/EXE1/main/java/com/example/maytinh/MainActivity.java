package com.example.maytinh;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private Button btnkhong;
    private Button btncham;
    private Button btnbang;
    private Button btncong;
    private Button btnmot;
    private Button btnhai;
    private Button btnba;
    private Button btntru;
    private Button btnbon;
    private Button btnnam;
    private Button btnsau;
    private Button btnnhan;
    private Button btnbay;
    private Button btntam;
    private Button btnchin;
    private Button btnchia;
    private Button btnc;
    private Button btndelete;
    private TextView tvmanghinh;
    private TextView tvkq;
RecyclerView recyclerView;
    Adapter adapter;
    ArrayList<Maytinh> data = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setControl();
        setEvent();
        setAdapter();
    }
public void setAdapter()
    {
        recyclerView = findViewById(R.id.recycleview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new Adapter(this,data,R.layout.list_item_layout);
        recyclerView.setAdapter(adapter);
    }
    public void setControl() {
        btnkhong = (Button) findViewById(R.id.btnkhong);
        btncham = (Button) findViewById(R.id.btncham);
        btnbang = (Button) findViewById(R.id.btnbang);
        btncong = (Button) findViewById(R.id.btncong);
        btnmot = (Button) findViewById(R.id.btnmot);
        btnhai = (Button) findViewById(R.id.btnhai);
        btnba = (Button) findViewById(R.id.btnba);
        btntru = (Button) findViewById(R.id.btntru);
        btnbon = (Button) findViewById(R.id.btnbon);
        btnnam = (Button) findViewById(R.id.btnnam);
        btnsau = (Button) findViewById(R.id.btnsau);
        btnnhan = (Button) findViewById(R.id.btnnhan);
        btnbay = (Button) findViewById(R.id.btnbay);
        btntam = (Button) findViewById(R.id.btntam);
        btnchin = (Button) findViewById(R.id.btnchin);
        btnchia = (Button) findViewById(R.id.btnchia);
        btndelete = (Button) findViewById(R.id.btndelete);
        tvmanghinh = (TextView) findViewById(R.id.tvmanghinh);
        tvkq = (TextView) findViewById(R.id.tvkq);
        btnc = (Button) findViewById(R.id.btnclean);
    }

    public void setEvent() {
        btnkhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //append(dung de set du lieu noi tiep nhau"#setText")
                tvmanghinh.append("0");
            }
        });
        btncham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvmanghinh.append(".");
            }
        });
        btncong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvmanghinh.append("+");
            }
        });
        btnmot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvmanghinh.append("1");
            }
        });
        btnhai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvmanghinh.append("2");
            }
        });
        btnba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvmanghinh.append("3");
            }
        });
        btntru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvmanghinh.append("-");
            }
        });
        btnbon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvmanghinh.append("4");
            }
        });
        btnnam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvmanghinh.append("5");
            }
        });
        btnsau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvmanghinh.append("6");
            }
        });
        btnnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvmanghinh.append("*");
            }
        });
        btnbay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvmanghinh.append("7");
            }
        });
        btntam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvmanghinh.append("8");
            }
        });
        btnchin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvmanghinh.append("9");
            }
        });
        btnchia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvmanghinh.append("/");
            }
        });
        btnc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvmanghinh.getText().toString() != "")
                {
                    tvmanghinh.setText("");
                    tvkq.setText("0");
                }else
                {
                    Toast.makeText(MainActivity.this, "Trống", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvmanghinh.getText().toString() != "")
                {
                    String delete = delete(tvmanghinh.getText().toString());
                    tvmanghinh.setText(delete);
                }else 
                {
                    Toast.makeText(MainActivity.this, "Trống", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnbang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Maytinh mt = new Maytinh();
                mt.setChuoi(tvmanghinh.getText().toString());

                DecimalFormat df = new DecimalFormat("###.#######");
                double result = 0;
                addPhepTinh(tvmanghinh.getText().toString());
                addSo(tvmanghinh.getText().toString());
                if(arrPhepTinh.size()>=arrSo.size() ||arrPhepTinh.size()<1){
                    Toast.makeText(MainActivity.this, "Lỗi!!!", Toast.LENGTH_SHORT).show();
                }else {
                    for (int i = 0; i < (arrSo.size() - 1); i++) {
                        switch (arrPhepTinh.get(i)) {
                            case "+":
                                if (i == 0) {
                                    result = arrSo.get(i) + arrSo.get(i + 1);
                                } else {
                                    result = result + arrSo.get(i + 1);
                                }
                                break;
                            case "-":
                                if (i == 0) {
                                    result = arrSo.get(i) - arrSo.get(i + 1);
                                } else {
                                    result = result - arrSo.get(i + 1);
                                }
                                break;
                            case "*":
                                if (i == 0) {
                                    result = arrSo.get(i) * arrSo.get(i + 1);
                                } else {
                                    result = result * arrSo.get(i + 1);
                                }
                                break;
                            case "/":
                                if (i == 0) {
                                    result = arrSo.get(i) / arrSo.get(i + 1);
                                } else {
                                    result = result / arrSo.get(i + 1);
                                }
                                break;
                            default:
                                break;
                        }
                    }
                        tvkq.setText(df.format(result) + "");
                        mt.setKq(tvkq.getText().toString());
                        data.add(mt);
                        adapter.notifyDataSetChanged();
                }
            }
        });
    }


    //mang chua cac phep tinh(+,-,*,/)
    public ArrayList<String> arrPhepTinh;

    //mang chua cac so
    public ArrayList<Double> arrSo;


    //lay cac phep tinh luu vao mang arrPhepTinh
    public int addPhepTinh(String input) {
        arrPhepTinh = new ArrayList<>();

        //lay tung ki tu trong mang luu vao cArray
        char[] cArray = input.toCharArray();

        //chay vong lap den het so ki tu, luu các phep tinh khi gap
        for (int i = 0; i < cArray.length; i++) {
            switch (cArray[i]) {
                case '+':
                    arrPhepTinh.add(cArray[i] + "");
                    break;
                case '-':
                    arrPhepTinh.add(cArray[i] + "");
                    break;
                case '*':
                    arrPhepTinh.add(cArray[i] + "");
                    break;
                case '/':
                    arrPhepTinh.add(cArray[i] + "");
                    break;
                default:
                    break;
            }
        }
        return 0;
    }

    //lay cac so luu vao mang arrSo
    public void addSo(String stringInput) {
        arrSo = new ArrayList<>();
        //thu vien mat dinh cua java, khong lay cac phep tinh ("(\\d+(?:\\.\\d+)?)"), lay ra cac con so
        Pattern regex = Pattern.compile("(\\d+(?:\\.\\d+)?)");
        Matcher matcher = regex.matcher(stringInput);
        while (matcher.find()){
            //luu so vao arrSo
            arrSo.add(Double.valueOf(matcher.group(1)));
        }
    }
    //ham delete ki tu
    public String delete(String number){
        int lenght = number.length();
        String tem = number.substring(0,lenght-1);
        return tem;
    }
}

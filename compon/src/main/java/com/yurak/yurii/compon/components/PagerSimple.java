package com.yurak.yurii.compon.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yurak.yurii.compon.R;
import com.yurak.yurii.compon.interfaces_classes.IComponent;
import com.yurak.yurii.compon.json_simple.ListRecords;
import com.yurak.yurii.compon.json_simple.Record;
import com.yurak.yurii.compon.models.WorkWithRecordsAndViews;

public class PagerSimple extends ViewPager implements IComponent {
    private int ITEM_LAYOUT_ID, INDICATOR;
    private Context context;
    protected ListRecords items;
    private PagerIndicator indicator;

    public PagerSimple(Context context) {
        super(context);
        this.context = context;
    }

    public PagerSimple(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        setAttribute(attrs);
    }

    private void setAttribute(AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PagerSimple);
        ITEM_LAYOUT_ID = a.getResourceId(R.styleable.PagerSimple_itemLayoutId, 0);
        INDICATOR = a.getResourceId(R.styleable.PagerSimple_indicator, 0);
//        Log.d("QWERT","setAttribute INDICATOR="+INDICATOR);
        a.recycle();
    }

    @Override
    public void setData(Object data) {
        items = (ListRecords) data;
        if (INDICATOR != 0) {
//            View root = findViewById(android.R.id.content).getRootView();
            View v = (View) getParent();
            indicator = (PagerIndicator) v.findViewById(INDICATOR);
            indicator.setCount(items.size());
        }
        setOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (INDICATOR != 0) {
                    indicator.setSelect(position);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        setAdapter(adapter);
    }

    PagerAdapter adapter = new PagerAdapter() {
        WorkWithRecordsAndViews modelToView = new WorkWithRecordsAndViews();
        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object instantiateItem(ViewGroup viewGroup, int position) {
            LayoutInflater inflater = LayoutInflater.from(context);
            ViewGroup v = (ViewGroup) ((LayoutInflater) context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE)).inflate(ITEM_LAYOUT_ID, null);
            Record record = items.get(position);
            modelToView.RecordToView(record, v);
            viewGroup.addView(v);
            return v;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup viewGroup, int position, Object view) {
            viewGroup.removeView((View) view);
        }
    };
}

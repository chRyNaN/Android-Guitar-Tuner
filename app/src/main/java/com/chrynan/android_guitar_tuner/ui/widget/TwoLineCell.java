package com.chrynan.android_guitar_tuner.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.chrynan.android_guitar_tuner.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A custom widget view class that displays two lines of text in a cell format.
 */
public class TwoLineCell extends ConstraintLayout {

    @BindView(R.id.twoLineCellFirstLineTextView)
    TextView firstLineTextView;
    @BindView(R.id.twoLineCellSecondLineTextView)
    TextView secondLineTextView;

    private String firstLineText;
    private String secondLineText;

    public TwoLineCell(Context context) {
        this(context, null);
    }

    public TwoLineCell(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TwoLineCell(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutInflater.from(context).inflate(R.layout.layout_two_line_cell, this);

        ButterKnife.bind(this);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TwoLineCell, defStyleAttr, 0);

        firstLineText = typedArray.getString(R.styleable.TwoLineCell_firstLineText);
        secondLineText = typedArray.getString(R.styleable.TwoLineCell_secondLineText);

        typedArray.recycle();

        firstLineTextView.setText(firstLineText);
        secondLineTextView.setText(secondLineText);

        setClickable(true);
    }

    /**
     * Retrieves the text of the first line of this view.
     *
     * @return The first line text.
     */
    public String getFirstLineText() {
        return firstLineText;
    }

    /**
     * Sets the first line text of this view.
     *
     * @param text The text to be set.
     */
    public void setFirstLineText(final String text) {
        this.firstLineText = text;
        this.firstLineTextView.setText(text);
    }

    /**
     * Retrieves the text of the second line of this view.
     *
     * @return The second line text.
     */
    public String getSecondLineText() {
        return secondLineText;
    }

    /**
     * Sets the second line text of this view.
     *
     * @param text The text to be set.
     */
    public void setSecondLineTextView(final String text) {
        this.secondLineText = text;
        this.secondLineTextView.setText(text);
    }
}

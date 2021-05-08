package ma4174h.gre.ac.uk.eztrade;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Point;


import java.util.HashSet;
import java.util.Set;

//public class GifSizeFilter extends Filter {

//    private int mMinWidth;
//    private int mMinHeight;
//    private int mMaxSize;
//
//    public GifSizeFilter(int minWidth, int minHeight, int maxSizeInBytes) {
//        mMinWidth = minWidth;
//        mMinHeight = minHeight;
//        mMaxSize = maxSizeInBytes;
//    }
//
//    @Override
//    public Set<MimeType> constraintTypes() {
//        return new HashSet<MimeType>() {{
//            add(MimeType.GIF);
//        }};
//    }
//
//    @Override
//    public IncapableCause filter(Context context, Item item) {
//        if (!needFiltering(context, item))
//            return null;
//
//        Point size = PhotoMetadataUtils.getBitmapBound(context.getContentResolver(), item.getContentUri());
//        if (size.x < mMinWidth || size.y < mMinHeight || item.size > mMaxSize) {
//            IncapableCause error = new IncapableCause(IncapableCause.DIALOG, "Error", "Details :"  + mMinWidth +
//                    PhotoMetadataUtils.getSizeInMB(mMaxSize));
//            return error;
//        }
//        return null;
//    }
//}

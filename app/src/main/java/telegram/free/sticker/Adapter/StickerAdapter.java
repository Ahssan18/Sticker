package telegram.free.sticker.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import telegram.free.sticker.Networking.Model.ResponseData;
import telegram.free.sticker.PrefrenceManager;
import telegram.free.sticker.R;

public class StickerAdapter extends RecyclerView.Adapter<StickerAdapter.StickerView> {
    private Context context;
    private List<ResponseData> list;

    public StickerAdapter(Context context, List<ResponseData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public StickerView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.custom_card, parent, false);
        return new StickerView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StickerView holder, int position) {
        Log.d("img_url",list.get(position).getImageurl());
        Glide
                .with(context)
                .load(list.get(position).getImageurl())
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.ivPic);
        holder.tvName.setText(list.get(position).getName());
        if(PrefrenceManager.getInstance(context).getTheme())
        {
            holder.btnAdd.setBackgroundColor(context.getResources().getColor(R.color.night_purple_200));
            holder.btnAdd.setTextColor(Color.WHITE);
        }else
        {
            holder.btnAdd.setBackgroundColor(context.getResources().getColor(R.color.purple_200));
//            holder.btnAdd.setTextColor(Color.BLACK);
        }
        holder.btnAdd.setOnClickListener(v -> {
            Uri uri = Uri.parse(list.get(position).getUrl()); // missing 'http://' will cause crashed
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class StickerView extends RecyclerView.ViewHolder {

        private CircleImageView ivPic;
        private TextView tvName;
        private Button btnAdd;
        public StickerView(@NonNull View itemView) {
            super(itemView);
            ivPic=itemView.findViewById(R.id.pic);
            tvName=itemView.findViewById(R.id.tv_name);
            btnAdd=itemView.findViewById(R.id.btn_add);
        }
    }
}

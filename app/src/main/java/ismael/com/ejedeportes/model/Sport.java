package ismael.com.ejedeportes.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ismael on 10/12/16.
 */

public class Sport implements Parcelable {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String name;
    private int image;
    private boolean[] selected;

    protected Sport(Parcel in) {
        id = in.readInt();
        name = in.readString();
        image = in.readInt();
        in.readBooleanArray(selected);
    }

    public static final Creator<Sport> CREATOR = new Creator<Sport>() {
        @Override
        public Sport createFromParcel(Parcel in) {
            return new Sport(in);
        }

        @Override
        public Sport[] newArray(int size) {
            return new Sport[size];
        }
    };

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return selected[0];
    }

    public void setSelected(boolean selected) {
        this.selected[0] = selected;
    }


    public Sport(int id, String name, int image, boolean selected) {
        this.id = id;
        this.selected = new boolean[1];
        this.name = name;
        this.image = image;
        this.selected[0] = selected;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeInt(this.image);
        dest.writeBooleanArray(new boolean[]{this.isSelected()});
    }
}

package com.philipplgh.sqlight.Mapper.Android.ContentProvider;

/**
 * Created by philipp on 12/20/2016.
 */
//https://developer.android.com/reference/android/provider/package-summary.html
public class ContentMappers {


    public static class MediaStore{

        public static class Audio{

            //Columns for audio file that show up in multiple tables.
            public static class AudioColumns {

                //https://developer.android.com/reference/android/provider/BaseColumns.html
                //region #####################  B A S E   C O L U M N S  ########################

                //The count of rows in a directory.
                public int _count;

                //The unique ID for a row.
                public long _id;
                //endregion

                //https://developer.android.com/reference/android/provider/MediaStore.MediaColumns.html
                //region #####################  M E D I A   C O L U M N S  ########################

                //Path to the file on disk.
                //Note that apps may not have filesystem permissions to directly access this path.
                //Instead of trying to open this path directly, apps should use openFileDescriptor(Uri, String) to gain access.
                public String _data;

                //The time the file was added to the media provider Units are seconds since 1970.
                public long date_added;

                //The time the file was last modified Units are seconds since 1970.
                //This is for internal use by the media scanner. Do not modify this field.
                public long date_modified;

                //The display name of the file
                public String _display_name;

                //The height of the image/video in pixels.
                public int height;

                //The width of the image/video in pixels.
                public int width;

                //The MIME type of the file
                public String mime_type;

                //The size of the file in bytes
                public long _size;

                /**
                 * The title of the content
                 */
                public String title;

                //endregion

                //https://developer.android.com/reference/android/provider/MediaStore.Audio.AudioColumns.html
                //region #####################  A U D I O   C O L U M N S  ########################

                /**
                 * The album the audio file is from, if any
                 */
                public String album;

                /**
                 * The id of the album the audio file is from, if any
                 */
                public long album_id;

                /**
                 * A non human readable key calculated from the ALBUM, used for searching, sorting and grouping
                 */
                public String album_key;

                //The artist who created the audio file, if any
                public String artist;

                //The id of the artist who created the audio file, if any
                public long artist_id;

                //A non human readable key calculated from the ARTIST, used for searching, sorting and grouping
                public String artist_key;

                //The position, in ms, playback was at when playback for this file was last stopped.
                public long bookmark;

                //The composer of the audio file, if any
                public String composer;

                //The duration of the audio file, in ms
                public long duration;

                //Non-zero if the audio file may be an alarm
                public boolean is_alarm;

                //Non-zero if the audio file is music
                public boolean is_music;

                //Non-zero if the audio file may be a notification sound
                public boolean is_notification;

                //Non-zero if the audio file is a podcast
                public boolean is_podcast;

                //Non-zero if the audio file may be a ringtone
                public boolean is_ringtone;

                //A non human readable key calculated from the TITLE, used for searching, sorting and grouping
                public String title_key;

                //The track number of this song on the album, if any.
                public int track;

                //The year the audio file was recorded, if any
                public int year;


                //endregion

            }

            //
            public static class ArtistColumns{
                public String artist;
                public String artist_key;
                public String number_of_albums;
                public String number_of_tracks;
            }


            //https://developer.android.com/reference/android/provider/MediaStore.Audio.AlbumColumns.html
            public static class AlbumColumns{
                /**
                 * The album on which the audio file appears, if any
                 */
                public String album;

                /**
                 * Cached album art.
                 */
                public String album_art;

                //The id for the album
                public long album_id;
                //A non human readable key calculated from the ALBUM, used for searching, sorting and grouping
                public String album_key;
                //The artist whose songs appear on this album
                public String artist;
                //The year in which the earliest songs on this album were released.
                public String minyear;
                //The year in which the latest songs on this album were released.
                public String maxyear;
                //The number of songs on this album
                public int numsongs;
                //This column is available when getting album info via artist, and indicates the number of songs on the album by the given artist.
                public String numsongs_by_artist;
            }


        }




    }







}

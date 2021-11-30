package DAL.DB;


import BE.Author;
import BE.CategorySong;
import BE.PlayList;
import BE.Song;
import DAL.ConnectionManager;
import DAL.interfaces.IPlayListDataAccess;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayListDAO implements IPlayListDataAccess {
    private ConnectionManager cm;

    public PlayListDAO() throws Exception {
        cm = new ConnectionManager();
    }

    @Override
    public PlayList getPlayList(int idPlayList) throws Exception {
        HashMap<Integer,Author> mapAuthor = getMapAuthor();
        HashMap<Integer,CategorySong> mapCategory = getMapCategory();
        PlayList playListSearched = null;
        HashMap<Integer,Song> songList = new HashMap<>();
        try (Connection con = cm.getConnection()) {
            String sqlcommandSelect = "SELECT Song.id as idSong, Song.name as songName, Song.authorID as AuthorID, Song.categoryID as CategoryID, " +
                                        " Song.songFile as songFile, " +
                                        " CORR_SONG_PLAYLIST.rankSong as RankSong,PlayList.name as playListName " +
                                        " FROM Song,CORR_SONG_PLAYLIST,Playlist " +
                                        " WHERE Song.id = CORR_SONG_PLAYLIST.songID " +
                                        " AND CORR_SONG_PLAYLIST.playlistID = PLAYLIST.id " +
                                        " AND CORR_SONG_PLAYLIST.playListID=? " +
                                        " ORDER BY RankSong;";
            PreparedStatement pstmtSelect = con.prepareStatement(sqlcommandSelect);
            pstmtSelect.setInt(1,idPlayList);
            ResultSet rs = pstmtSelect.executeQuery();
            String playListName = null;
            while(rs.next())
            {
                songList.put(rs.getInt("RankSong"), new Song(
                        rs.getInt("idSong"),
                        rs.getString("songName"),
                        mapAuthor.get(rs.getInt("authorID")),
                        mapCategory.get(rs.getInt("categoryID")),
                        rs.getString("songFile")
                    )
                );
                playListName = rs.getString("playListName");
            }
            //System.out.println("SIZE: "+songList.size()+" id : "+idPlayList+" name: "+playListName);
            playListSearched = new PlayList(idPlayList,playListName,songList);
        }

        return playListSearched;
    }

    @Override
    public List<PlayList> getALlPlayLists() throws Exception {
        HashMap<Integer,Author> mapAuthor = getMapAuthor();
        HashMap<Integer,CategorySong> mapCategory = getMapCategory();
        PlayList playListSearched = null;
        List<PlayList> allPlayLists = new ArrayList<>();
        HashMap<Integer,Song> songList = new HashMap<>();
        try (Connection con = cm.getConnection()) {
            String sqlcommandSelect = "SELECT PLAYLIST.id as playListID, Song.id as idSong, Song.name as songName, Song.authorID as AuthorID, Song.categoryID as CategoryID, " +
                    "Song.songFile as songFile, CORR_SONG_PLAYLIST.rankSong as RankSong,PlayList.name as playListName " +
                    "FROM Song INNER JOIN CORR_SONG_PLAYLIST " +
                    "ON Song.id=CORR_SONG_PLAYLIST.songID " +
                    "INNER JOIN Playlist ON CORR_SONG_PLAYLIST.playListID=PLAYLIST.id " +
                    "ORDER BY PlayList.id,RankSong ";
            PreparedStatement pstmtSelect = con.prepareStatement(sqlcommandSelect);
            boolean flagFirst = false;
            int currentPlaylist = -1;
            ResultSet rs = pstmtSelect.executeQuery();
            int idPlayList=0;
            String playListName = null;
            while(rs.next())
            {
                idPlayList=rs.getInt("playlistID");
                if(!flagFirst) {
                    currentPlaylist=idPlayList;
                    flagFirst=true;
                }
                if(currentPlaylist!= idPlayList) {
                    PlayList playList = new PlayList(currentPlaylist,playListName,songList);
                    allPlayLists.add(playList);
                    songList.clear();
                }
                songList.put(rs.getInt("RankSong"), new Song(
                                rs.getInt("idSong"),
                                rs.getString("songName"),
                                mapAuthor.get(rs.getInt("authorID")),
                                mapCategory.get(rs.getInt("categoryID")),
                                rs.getString("songFile")
                        )
                );
                playListName = rs.getString("playListName");
                currentPlaylist=idPlayList;

            }
            //System.out.println("SIZE: "+songList.size()+" id : "+idPlayList+" name: "+playListName);
            PlayList playList = new PlayList(idPlayList,playListName,songList);
            allPlayLists.add(playList);
        }

        return allPlayLists;
    }
 //TODO: Not tested, will test it tomorrow, too tired now...
    @Override
    public PlayList createPlayList(PlayList playList) throws Exception {
        PlayList playListCreated=null;
        try (Connection con = cm.getConnection()) {
            String sqlcommandInsert = "INSERT INTO PLAYLIST VALUES (?);";

            PreparedStatement pstmtInsert = con.prepareStatement(sqlcommandInsert, Statement.RETURN_GENERATED_KEYS);
            pstmtInsert.setString(1,playList.getName());
            pstmtInsert.execute();
            ResultSet rs = pstmtInsert.getGeneratedKeys();
            int idPlayList = 0;
            while(rs.next()) {
                idPlayList=rs.getInt(1);
            }

            String sqlCommandInsertListSong = "INSERT INTO CORR_SONG_PLAYLIST VALUES (?,?,?)";
            PreparedStatement pstmstInsertListSong = con.prepareStatement(sqlCommandInsertListSong);
            for (Map.Entry entry: playList.getListSong().entrySet()) {
                Song song = (Song) entry.getValue();
                pstmstInsertListSong.setInt(1,song.getId());
                pstmstInsertListSong.setInt(2,playList.getIdPlaylist());
                pstmstInsertListSong.setInt(3,(int)entry.getKey());
                System.out.println("ID SONG: "+song.getId()+" ID PLAYLIST: "+playList.getIdPlaylist()+" RANKSONG: "+(int)entry.getKey());
                pstmstInsertListSong.executeQuery();
            }
            playListCreated = getPlayList(idPlayList);

        }
        return playListCreated;

    }

    @Override
    public void updatePlayList(PlayList playList) throws Exception {

    }

    @Override
    public void deletePlayList(PlayList playList) throws Exception {

    }

    private HashMap<Integer, Author> getMapAuthor() throws Exception {
        AuthorDAO authorDAO = new AuthorDAO();
        List<Author> allAuthors = authorDAO.getALlAuthors();
        HashMap<Integer,Author> authorMap = new HashMap<>();
        for (Author aut: allAuthors) {
            if(!authorMap.containsKey(aut.getId())) {
                authorMap.put(aut.getId(),aut);
            }
        }
        return authorMap;
    }
    private HashMap<Integer, CategorySong> getMapCategory() throws Exception {
        CategoryDAO categoryDAO = new CategoryDAO();
        List<CategorySong> allCategories = categoryDAO.getALlCategorySong();
        HashMap<Integer,CategorySong> categoryMap = new HashMap<>();
        for (CategorySong cat: allCategories) {
            if(!categoryMap.containsKey(cat.getId())) {
                categoryMap.put(cat.getId(),cat);
            }
        }
        return categoryMap;
    }
}

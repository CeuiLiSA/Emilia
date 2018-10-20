package ceuilisa.mirai.interf;

public interface MusicOperate {
    void playMusic(int id, OnMusicPrepare onMusicPrepare);

    void stopOrPlay();

    boolean isPlayingMusic();
}

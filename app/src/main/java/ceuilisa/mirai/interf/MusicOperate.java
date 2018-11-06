package ceuilisa.mirai.interf;

public interface MusicOperate {
    void playMusic(int id, OnPrepare onPrepare);

    void stopOrPlay();

    boolean isPlayingMusic();
}

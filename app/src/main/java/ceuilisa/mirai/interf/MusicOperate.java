package ceuilisa.mirai.interf;

public interface MusicOperate {

    void playMusic(long id, OnPrepare onPrepare);

    void stopOrPlay();

    boolean isPlayingMusic();
}

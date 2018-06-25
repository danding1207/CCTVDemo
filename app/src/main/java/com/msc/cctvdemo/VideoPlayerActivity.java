package com.msc.cctvdemo;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.offline.FilteringManifestParser;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistParser;
import com.google.android.exoplayer2.source.hls.playlist.RenditionKey;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.upstream.FileDataSourceFactory;
import com.google.android.exoplayer2.upstream.HttpDataSource;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.upstream.cache.Cache;
import com.google.android.exoplayer2.upstream.cache.CacheDataSource;
import com.google.android.exoplayer2.upstream.cache.CacheDataSourceFactory;
import com.google.android.exoplayer2.upstream.cache.NoOpCacheEvictor;
import com.google.android.exoplayer2.upstream.cache.SimpleCache;
import com.google.android.exoplayer2.util.EventLogger;
import com.google.android.exoplayer2.util.Util;

import java.io.File;
import java.util.Collections;
import java.util.List;

public class VideoPlayerActivity extends AppCompatActivity {

    private static final DefaultBandwidthMeter BANDWIDTH_METER = new DefaultBandwidthMeter();

    protected String userAgent;
    private SimpleExoPlayer player;
    private DataSource.Factory mediaDataSourceFactory;
    private MediaSource mediaSource;
    private DefaultTrackSelector trackSelector;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_video_player);

        userAgent = Util.getUserAgent(this, "com.msc.cctvdemo");


        PlayerView playerView = findViewById(R.id.playerView);


        // step1. 创建一个默认的TrackSelector
        Handler mainHandler = new Handler();


        // 创建轨道选择工厂
        TrackSelection.Factory videoTrackSelectionFactory = new
                AdaptiveTrackSelection.Factory(BANDWIDTH_METER);

        // 创建轨道选择器实例
        trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);

        //step2. 创建播放器
        player = ExoPlayerFactory.newSimpleInstance(this, trackSelector);

        player.setPlayWhenReady(true);
        player.addAnalyticsListener(new EventLogger(trackSelector));
        playerView.setPlayer(player);
//        playerView.setPlaybackPreparer(this);

        Uri uri = Uri.parse(getIntent().getStringExtra("tvUri"));

        mediaDataSourceFactory = buildDataSourceFactory();

        mediaSource = new HlsMediaSource.Factory(mediaDataSourceFactory)
                .setPlaylistParser(
                        new FilteringManifestParser<>(
                                new HlsPlaylistParser(),
                                getOfflineStreamKeys(uri)))
                .createMediaSource(uri);

        // Prepare
        player.prepare(mediaSource);

    }

    @Override
    protected void onStop() {
        super.onStop();

        if (Util.SDK_INT > 23) {
            releasePlayer();
        }
    }

    private void releasePlayer() {
        if (player != null) {
            player.release();
            player = null;
            mediaSource = null;
            trackSelector = null;
        }
    }

    /**
     * Returns a new DataSource factory.
     *
     * @return A new DataSource factory.
     */
    private DataSource.Factory buildDataSourceFactory() {
        return buildDataSourceFactory(BANDWIDTH_METER);
    }

    /**
     * Returns a {@link DataSource.Factory}.
     */
    public DataSource.Factory buildDataSourceFactory(TransferListener<? super DataSource> listener) {
        DefaultDataSourceFactory upstreamFactory =
                new DefaultDataSourceFactory(this, listener, buildHttpDataSourceFactory(listener));
        return upstreamFactory;
    }

    /**
     * Returns a {@link HttpDataSource.Factory}.
     */
    public HttpDataSource.Factory buildHttpDataSourceFactory(
            TransferListener<? super DataSource> listener) {
        return new DefaultHttpDataSourceFactory(userAgent, listener);
    }

    @SuppressWarnings("unchecked")
    public List<RenditionKey> getOfflineStreamKeys(Uri uri) {
        return Collections.emptyList();
    }

}

package com.hscrm.dao;

import com.hscrm.domain.Track;

import java.util.List;

/**
 * @author Peter Cheung
 * @user PerCheung
 * @date 2021/9/7 16:46
 */
public interface TrackDao {
    /**
     * 增加跟踪记录
     */
    int addTrack(Track track);

    /**
     * 删除跟踪记录
     */
    int deleteTrack(int t_id);

    /**
     * 修改跟踪记录
     */
    int updateTrack(Track track);

    /**
     * 查询跟踪记录
     */
    Track findTrack(int t_id);

    /**
     * 查询所有
     */
    List<Track> findAllTrack(String username);

    /**
     * 根据客户ID删除
     */
    int deleteTrackByCid(int c_id);
}

package com.hscrm.service.impl;

import com.hscrm.dao.TrackDao;
import com.hscrm.dao.impl.TrackDaoImpl;
import com.hscrm.domain.Track;
import com.hscrm.service.TrackService;

import java.util.List;

/**
 * @author Peter Cheung
 * @user PerCheung
 * @date 2021/9/7 17:44
 */
public class TrackServiceImpl implements TrackService {
    private TrackDao trackDao = new TrackDaoImpl();

    @Override
    public int addTrack(Track track) {
        return trackDao.addTrack(track);
    }

    @Override
    public int deleteTrack(int t_id) {
        return trackDao.deleteTrack(t_id);
    }

    @Override
    public int updateTrack(Track track) {
        return trackDao.updateTrack(track);
    }

    @Override
    public Track findTrack(int t_id) {
        return trackDao.findTrack(t_id);
    }

    @Override
    public List<Track> findAllTrack(String username) {
        return trackDao.findAllTrack(username);
    }
}

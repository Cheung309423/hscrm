package com.hscrm.dao.impl;

import com.hscrm.dao.TrackDao;
import com.hscrm.domain.Customer;
import com.hscrm.domain.Emp;
import com.hscrm.domain.Track;
import com.hscrm.util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Peter Cheung
 * @user PerCheung
 * @date 2021/9/7 17:00
 */
public class TrackDaoImpl implements TrackDao {
    private DBUtil db = new DBUtil();

    @Override
    public int addTrack(Track track) {
        String sql = "insert into track values(null,?,?,?,?)";
        Object[] objs = {track.getCustomer().getC_id(), track.getEmp().getE_id(), track.getRecord(), track.getIntention()};
        int i = db.update(sql, objs);
        db.close();
        return i;
    }

    @Override
    public int deleteTrack(int t_id) {
        String sql = "delete from track where t_id = ?";
        Object[] objs = {t_id};
        int i = db.update(sql, objs);
        db.close();
        return i;
    }

    @Override
    public int updateTrack(Track track) {
        String sql = "update track set c_id = ?,record=?,intention=? where t_id=?";
        Object[] objs = {track.getCustomer().getC_id(), track.getRecord(), track.getIntention(), track.getT_id()};
        int i = db.update(sql, objs);
        db.close();
        return i;
    }

    @Override
    public Track findTrack(int t_id) {
        String sql = "select * from track t,emp e,customer c where t.c_id=c.c_id and t.e_id=e.e_id and t_id = ?";
        Object[] objs = {t_id};
        ResultSet rs = db.select(sql, objs);
        Customer customer = null;
        Emp emp = null;
        Track track = null;
        try {
            if (rs.next()) {
                customer = new Customer(
                        rs.getInt("c.c_id"),
                        rs.getString("c_name"),
                        rs.getString("c_sex"),
                        rs.getString("c_tel"),
                        rs.getString("c_job"),
                        rs.getString("c_company")
                );
                emp = new Emp(
                        rs.getInt("e.e_id"),
                        rs.getString("e_name"),
                        rs.getString("e_sex"),
                        rs.getString("e_tel"),
                        rs.getString("username")
                );
                track = new Track(
                        rs.getInt("t_id"),
                        customer,
                        emp,
                        rs.getString("record"),
                        rs.getString("intention")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return track;
    }

    @Override
    public List<Track> findAllTrack(String username) {
        String sql = "select * from track t,emp e,customer c where t.c_id=c.c_id and t.e_id=e.e_id and username=?";
        Object[] objs = {username};
        ResultSet rs = db.select(sql, objs);
        List<Track> trackList = new ArrayList<>();
        Customer customer = null;
        Emp emp = null;
        Track track = null;
        try {
            while (rs.next()) {
                customer = new Customer(
                        rs.getInt("c.c_id"),
                        rs.getString("c_name"),
                        rs.getString("c_sex"),
                        rs.getString("c_tel"),
                        rs.getString("c_job"),
                        rs.getString("c_company")
                );
                emp = new Emp(
                        rs.getInt("e.e_id"),
                        rs.getString("e_name"),
                        rs.getString("e_sex"),
                        rs.getString("e_tel"),
                        rs.getString("username")
                );
                track = new Track(
                        rs.getInt("t_id"),
                        customer,
                        emp,
                        rs.getString("record"),
                        rs.getString("intention")
                );
                trackList.add(track);
            }
        } catch (SQLException e) {

        } finally {
            db.close();
        }
        return trackList;
    }

    @Override
    public int deleteTrackByCid(int c_id) {
        String sql = "delete from track where c_id=?";
        Object[] objs = {c_id};
        int i = db.update(sql, objs);
        return i;
    }
}

package com.stackroute.muzixservice.repository;

import com.stackroute.muzixservice.domain.Track;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TrackRepositoryTest {

    @Autowired
    private TrackRepository trackRepository;
    private Track track;

    @Before
    public void setUp()
    {
        track = new Track();
        track.setTrackId(5);
        track.setTrackName("sai");
        track.setTrackComments("Reddy");

    }

    @After
    public void tearDown(){
        trackRepository.deleteAll();
    }


    @Test
    public void saveTrack(){
        trackRepository.save(track);
        Track fetchTrack = trackRepository.findById(track.getTrackId()).get();
        System.out.println(fetchTrack);
        Assert.assertEquals(5,fetchTrack.getTrackId());

    }

    @Test
    public void saveTrackFailure(){
        Track testTrack = new Track(1,"lucky","hahhahahhahhah");
        trackRepository.save(track);
        Track fetchTrack = trackRepository.findById(track.getTrackId()).get();
        Assert.assertNotSame(testTrack,track);
    }
    @Test
    public void GetAllTrack(){
        Track track1 = new Track(2,"fhgh","etryuu");
        Track track2 = new Track(3,"uioijj","dsdgh");
        trackRepository.save(track1);
        trackRepository.save(track2);

        List<Track> list = trackRepository.findAll();
        Assert.assertEquals(2,list.get(0).getTrackId());
    }
    @Test
    public void deleteTrack(){
        trackRepository.delete(track);
        Assert.assertEquals(false,trackRepository.existsById(track.getTrackId()));
    }
    @Test
    public void deleteTrackFailure(){
        trackRepository.delete(track);
        Assert.assertNotSame(true,trackRepository.existsById(track.getTrackId()));
    }
    @Test
    public void updateTrack(){
        trackRepository.save(track);
        trackRepository.findById(track.getTrackId()).get().setTrackComments("Hiiiiiii");
        Track list=trackRepository.getOne(track.getTrackId());
        Assert.assertEquals("Hiiiiiii",list.getTrackComments());
    }
    @Test
    public void updateTrackFailure(){
        trackRepository.save(track);
        trackRepository.findById(track.getTrackId()).get().setTrackComments("Hiiiiiii");
        Track list=trackRepository.getOne(track.getTrackId());
        Assert.assertNotSame("hiiiii",list.getTrackComments());
    }

    @Test
    public void retrieveTrackByName() {
       trackRepository.save(track);
        Assert.assertEquals(track,trackRepository.retrieveTrackByName("sai"));

    }
    @Test
    public void retrieveTrackByNameFailure() {
        trackRepository.save(track);
        Assert.assertNotSame(track,trackRepository.retrieveTrackByName("saiiiiiii"));

    }

}

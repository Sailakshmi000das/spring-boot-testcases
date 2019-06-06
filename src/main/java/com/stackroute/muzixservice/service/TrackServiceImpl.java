package com.stackroute.muzixservice.service;

import com.stackroute.muzixservice.domain.Track;
import com.stackroute.muzixservice.exception.TrackAlreadyExistsException;
import com.stackroute.muzixservice.exception.TrackNotFoundException;
import com.stackroute.muzixservice.repository.TrackRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackServiceImpl implements TrackService {

    TrackRepository trackRepository;

    public TrackServiceImpl(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExistsException {
        Track savedTrack = null;
        if (trackRepository.existsById(track.getTrackId())) {
            throw new TrackAlreadyExistsException("Track already exists");
        } else {
            savedTrack = trackRepository.save(track);
            if (savedTrack == null) {
                throw new TrackAlreadyExistsException("track not empty");
            }
            return savedTrack;

        }
    }

    @Override
    public List<Track> getAllTrack() {
        return trackRepository.findAll();
    }

    @Override
    public Track deleteTrack(int id) throws TrackNotFoundException{
        if (trackRepository.existsById(id)) {
            trackRepository.deleteById(id);

        } else {
            throw new TrackNotFoundException("Exception in deleteTrack");
        }
        return null;
    }

    @Override
    public Track retrieveTrackByName(String trackName) throws TrackNotFoundException {
        Track trackList=null;
        trackList=trackRepository.retrieveTrackByName(trackName);
        if(trackList==null){
            throw new TrackNotFoundException("track not found");
        }
        return trackList;
    }


    @Override
    public Track updateComments(Track track) throws TrackNotFoundException{
        Track track1=null;
        if (trackRepository.existsById(track.getTrackId())) {
            track.setTrackComments(track.getTrackComments());
            track1 = trackRepository.save(track);
            //            trackRepository.save(track1);
        } else {
            throw new TrackNotFoundException("track not found");
        }

        return track1;
    }
}

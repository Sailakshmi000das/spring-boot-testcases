package com.stackroute.muzixservice.repository;

import com.stackroute.muzixservice.domain.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackRepository extends JpaRepository<Track ,Integer> {

    @Query(value = "select t from Track t where t.trackName=:trackName")
    public Track retrieveTrackByName(@Param("trackName") String trackName);
}

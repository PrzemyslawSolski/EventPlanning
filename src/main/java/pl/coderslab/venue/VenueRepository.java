package pl.coderslab.venue;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.user.User;

import java.util.List;
import java.util.Set;


public interface VenueRepository extends JpaRepository<Venue, Long> {

    Set<Venue> findByTmp(byte tmp);

//    select distinct v.id, v.city, v.number, v.street, v.zip, v.name
//    from venues v
//    join events e on v.id = e.ceremonyVenue_id
//    or v.id = e.partyVenue_id
//    join event_user eu on e.id = eu.events_id
//    where users_id =3 ;
//
//
//    select e.partyVenue from User as u inner join u.events as e where u.id =?1
//
//    select e.events.partyVenue from User where u.id =?1

    @Query(value = "select distinct v.id, v.city, v.number, v.street, v.zip, v.name, v.tmp from venues as v join events as e on v.id = e.ceremonyVenue_id or v.id = e.partyVenue_id join event_user as eu on e.id = eu.events_id where users_id =?1", nativeQuery = true )
    List<Venue> getUserVenues(String userId);

    @Query("select e.partyVenue from User as u inner join u.events as e where u.id =?1")
    Set<Venue> getUserPartyVenuesSet(long id);

    @Query("select e.ceremonyVenue from User as u inner join u.events as e where u.id =?1")
    Set<Venue> getUserCeremonyVenuesSet(long id);

}

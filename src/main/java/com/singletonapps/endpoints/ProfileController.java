package com.singletonapps.endpoints;

import com.singletonapps.filter.BeanFilter;
import com.singletonapps.model.Profile;
import com.singletonapps.service.ProfileService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/profiles")
public class ProfileController {

    @Inject
    private ProfileService profileService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Profile> getProfiles(@BeanParam BeanFilter filter){

        if (filter.getYear() > 0){
            return profileService.getAllProfilesByYear(filter.getYear());
        }
        if (filter.getOffset() >= 0 && filter.getSize() > 0) {
            return profileService.getProfilesPaginated(filter.getOffset(), filter.getSize());
        }

        return profileService.getAllProfiles();
    }

    @GET
    @Path("/{profileId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Profile getProfile(@PathParam("profileId") Long id){

        return profileService.getProfile(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Profile addProfile(Profile profile){

        return profileService.addProfile(profile);
    }

    @PUT
    @Path("/{profileId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Profile updateProfile(@PathParam("profileId") long id, Profile profile){

        profile.setId(id);
        return profileService.updateProfile(profile);
    }

    @DELETE
    @Path("/{profileId}")
    @Produces(MediaType.APPLICATION_JSON)
    public void removeProfile(@PathParam("profileId") long id){

        profileService.removeProfile(id);
    }
}

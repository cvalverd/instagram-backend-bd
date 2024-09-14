package com.fullstack.instagram_backend_bd.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fullstack.instagram_backend_bd.api.request.ProfileInfoUpdateRequest;
import com.fullstack.instagram_backend_bd.model.ProfileInfo;
import com.fullstack.instagram_backend_bd.service.profileinfo.ProfileInfoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/profile-info")
@RequiredArgsConstructor
public class ProfileInfoController {

    private static final Logger logger = LoggerFactory.getLogger(ProfileInfoController.class);

    private final ProfileInfoService profileInfoService;

    @PatchMapping("/{id}")
    public ResponseEntity<ProfileInfo> patchProfileInfo(@PathVariable Long id,
            @RequestBody ProfileInfoUpdateRequest updateRequest) {
        logger.info("Updating profile info with id: {}", id);
        ProfileInfo updatedProfileInfo = profileInfoService.updateProfileInfo(id, updateRequest);
        logger.info("Updated profile info ID: {}", updatedProfileInfo.getId());
        return new ResponseEntity<>(updatedProfileInfo, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileInfo> getProfileInfoById(@PathVariable Long id) {
        logger.info("Getting profile info by id: {}", id);
        Optional<ProfileInfo> profileInfo = profileInfoService.findProfileInfoById(id);
        if (profileInfo.isPresent()) {
            logger.info("Found profile info id: {}", profileInfo.get().getId());
            return new ResponseEntity<>(profileInfo.get(), HttpStatus.OK);
        } else {
            logger.info("Profile info not found by id: {}", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProfileInfo>> getAllProfileInfos() {
        logger.info("Getting all profile infos");
        List<ProfileInfo> profileInfos = profileInfoService.findAllProfileInfos();
        logger.info("Found {} profile infos", profileInfos.size());
        return new ResponseEntity<>(profileInfos, HttpStatus.OK);
    }

    @DeleteMapping("/profile-info/{id}")
    public ResponseEntity<Void> deleteProfileInfo(@PathVariable Long id) {
        logger.info("Deleting profile info with id: {}", id);
        profileInfoService.deleteProfileInfoById(id);
        logger.info("Profile info deleted with id: {}", id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}


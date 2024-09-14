package com.fullstack.instagram_backend_bd.service.profileinfo;

import java.util.List;
import java.util.Optional;

import com.fullstack.instagram_backend_bd.api.request.ProfileInfoUpdateRequest;
import com.fullstack.instagram_backend_bd.model.ProfileInfo;

public interface ProfileInfoService {
   ProfileInfo updateProfileInfo(Long id, ProfileInfoUpdateRequest updateRequest);
   Optional<ProfileInfo> findProfileInfoById(Long id);
   List<ProfileInfo> findAllProfileInfos();
   void deleteProfileInfoById(Long id);
}

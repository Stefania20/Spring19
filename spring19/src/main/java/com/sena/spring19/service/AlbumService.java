package com.sena.spring19.service;

import java.util.List;

import com.sena.spring19.model.Album;

public interface AlbumService {
    public List<Album> findAll();
    public void save (Album album);
    public Album findOne(Integer id);
    public void delete(Integer id);
}



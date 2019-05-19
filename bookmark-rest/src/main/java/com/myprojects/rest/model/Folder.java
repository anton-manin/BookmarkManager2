package com.myprojects.rest.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="FOLDER")
public class Folder {
    private long folderId;
    private String name;

    private Folder parent;

    private List<Folder> children = new LinkedList<>();

    private Set<Bookmark> bookmarks = new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="FOLDER_ID", length=4, nullable=false)
    public long getFolderId() {
        return folderId;
    }
    public void setFolderId(long folderId) {
        this.folderId = folderId;
    }

    @Column(name="NAME", length=45, nullable=false)
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @JsonManagedReference
    @OneToMany(fetch=FetchType.LAZY, mappedBy="folder")
    public Set<Bookmark> getBookmarks() {
        return bookmarks;
    }
    public void setBookmarks(Set<Bookmark> bookmarks) {
        this.bookmarks = bookmarks;
    }

    @ManyToOne
    @JoinColumn(name="PARENT_ID")
    public Folder getParent() {
        return parent;
    }
    public void setParent(Folder parent) {
        this.parent = parent;
    }

    @OneToMany(fetch= FetchType.LAZY, mappedBy="parent")
    public List<Folder> getChildren() {
        return children;
    }
    public void setChildren(List<Folder> children) {
        this.children = children;
    }

    public void addChild(Folder folder) {
        if (this.children == null) {
            this.children = new LinkedList<>();
        }

        this.children.add(folder);
    }
}
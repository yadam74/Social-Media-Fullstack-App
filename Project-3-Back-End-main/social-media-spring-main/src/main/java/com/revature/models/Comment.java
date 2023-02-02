package com.revature.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String text;
    @ManyToOne(cascade = CascadeType.REFRESH)
    private User commenter;
    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinTable(name = "post_comment", joinColumns = @JoinColumn(name = "comment_id", referencedColumnName = "id", nullable = true), inverseJoinColumns = @JoinColumn(name = "post_id", referencedColumnName = "postId", nullable = true))
    @JsonIgnoreProperties("comments")
    private Post post;

}
package com.qa;



import com.jayway.restassured.RestAssured;
import org.junit.Test;

import java.util.List;

import static com.jayway.restassured.RestAssured.get;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

public class CommentTest {

    private final String baseURI = "https://jsonplaceholder.typicode.com";

    private List<Comment> getComments() {
        RestAssured.baseURI = baseURI;
        RestAssured.useRelaxedHTTPSValidation();
        return asList( get("/comments").getBody().as(Comment[].class));
    }

    @Test
    public void should_post_with_id_40_contains_5_comments() {
        List<Comment> comments = getComments().stream()
                .filter(comment -> comment.getPostId().equals(40)).collect(toList());

        assertThat(comments).hasSize(5);
    }

    @Test
    public void should_post_with_id_40_contains_comment() {
        List<Comment> comments = getComments().stream()
                .filter(comment ->
                        comment.getPostId().equals(40) &&
                        comment.getName().equals("pariatur aspernatur nam atque quis") &&
                        comment.getEmail().equals("Cooper_Boehm@damian.biz") &&
                        comment.getBody().equals("veniam eos ab voluptatem in fugiat ipsam quis\nofficiis non qui\nquia ut id voluptates et a molestiae commodi quam\ndolorem enim soluta impedit autem nulla")).collect(toList());

        assertThat(comments).hasSize(1);
    }

    private static class Comment{

        private Integer postId;
        private Integer id;
        private String name;
        private String email;
        private String body;


        public void setPostId(Integer postId) {
            this.postId = postId;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Integer getPostId() {
            return postId;
        }

        public Integer getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public String getBody() {
            return body;
        }

    }


}
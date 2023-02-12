package com.tunetrip.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

import java.util.Map;

@Service
public interface YouTube {

    @GetExchange("/search?part=snippet&type=video")
    //@GetExchange("/search?part=")
    SearchListResponse channelVideos( //
                                      @RequestParam String channelId, //
                                      @RequestParam int maxResults, //
                                      @RequestParam Sort order);

    enum Sort {
        DATE("date"), //
        VIEW_COUNT("viewCount"), //
        TITLE("title"), //
        RATING("rating");

        private final String type;

        Sort(String type) {
            this.type = type;
        }
    }

    record SearchListResponse(String kind, String etag, String nextPageToken, String prevPageToken, PageInfo pageInfo,
                              SearchResult[] items) {
    }
    record PageInfo(Integer totalResults, Integer resultsPerPage) {
    }
    record SearchResult(String kind, String etag, SearchId id, SearchSnippet snippet) {
    }

    record SearchId(String kind, String videoId, String channelId, String playlistId) {
    }

    /*record SearchSnippet(String publishedAt, String channelId, String title, String description,
                         Map<String, SearchThumbnail> thumbnails, String channelTitle) {
    }*/

    record SearchSnippet(String publishedAt, String channelId, String title, String description,
                         Map<String, SearchThumbnail> thumbnails, String channelTitle) {

        String shortDescription() {
            if (this.description.length() <= 100) {
                return this.description;
            }
            return this.description.substring(0, 100);
        }

        SearchThumbnail thumbnail() {
            return this.thumbnails.entrySet().stream() //
                    .filter(entry -> entry.getKey().equals("default")) //
                    .findFirst() //
                    .map(Map.Entry::getValue) //
                    .orElse(null);
        }
    }


    record SearchThumbnail(String url, Integer width, Integer height) {
    }

}

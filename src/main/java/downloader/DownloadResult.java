package downloader;

public class DownloadResult {

    static DownloadResult failure(String reason) {
        return new DownloadResult(null, Result.FAILURE, reason);
    }

    static DownloadResult success(String content) {
        return new DownloadResult(content, Result.SUCCESS, null);
    }

    public enum Result {
        SUCCESS,
        FAILURE
    }

    private Result result;
    private String content;
    private String failureReason;

    private DownloadResult(String content, Result result, String failureReason) {
        this.content = content;
        this.result = result;
        this.failureReason = failureReason;
    }

    public Result getResult() {
        return result;
    }

    public String getContent() {
        return content;
    }

    public String getFailureReason() {
        return failureReason;
    }
}

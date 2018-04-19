public class FirstFailingVersion {

    public static long firstBadVersion(long n, IsFailingVersion isBadVersion) {
        // TIME: O(log n) I think


        long r = n;
        // basically, cutting it in half every time
        long l = 1;
        while (l< r) {
            long mid = l + (r - l) / 2;
            if (isBadVersion.isFailingVersion(mid)) {
                // skip all the ones from mid to right
                r = mid;
            } else {
                // if that dosent work, skip all the ones from left to middle
                l = mid + 1;
            }
        }
        // if l is not smaller than r, then we have overlapped
        return l;
    }
}

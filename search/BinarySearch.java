package search;

public class BinarySearch {
    // Immutable: for all i arr[i] >= arr[i + 1] && for all i, j : i < j arr[i] >= arr[j]
    // Pre: Immutable
    // for all i, j : i < j arr[i] >= arr[j]
    // exist i : arr[i] <= x && i - min && i in [0, arr.length - 1]
    // Post: res in [0 ... arr.length]
    // if res == arr.length than arr[arr.length - 1] < x
    // else arr[res] <= x && (x < arr[res + 1] || res + 1 == arr.length)
    static int IterativeSearch(int x, int[] arr) {
        // for all i, j : i < j arr[i] >= arr[j]
        if (arr.length == 0 || arr[arr.length - 1] > x) {
            return arr.length;
        }
        // for all i, j : i < j arr[i] >= arr[j] && x >= arr[arr.length - 1]
        // arr.length > 0
        int l = -1, r = arr.length;
        // l < r

        // (arr[l] > x or not exist) && (x >= arr[r]) && r - l > 1
        while (l + 1 < r) {
            // (arr[l] > x or not exist) && (x >= arr[r]) && r - l > 1
            int m = (l + r) / 2;
            // l < m < r && arr[l] >= arr[m] >= arr[r]
            if (arr[m] > x) {
                //arr[m] > x && arr[l] >= arr[m] > arr[x]
                l = m;
                //arr[l] > x
            } else {
                //arr[m] <= x && arr[m] <= arr[r]
                r = m;
                //arr[r] <= x
            }
            //(arr[l] > x or not exist) && (x >= arr[r]) && (r - l) < (r' - l')
        }
        // (arr[l] > x or not exist) && (x >= arr[r]) && r - l == 1

        //  arr[r] <= x && r min
        return r;
    }

    // Pre: Immutable
    // for all i, j : i < j arr[i] >= arr[j]
    // exist i : arr[i] <= x && i - min && i in [0, arr.length - 1]
    // Post: r = i
    static int RecursiveSearch(int x, int[] arr, int l, int r) {
        // Pre: l < r && (arr[l] > x or arr[l] not exist) && x >= arr[r]
        // for all i, j : i < j arr[i] >= arr[j]
        if (arr.length == 0 || arr[arr.length - 1] > x) {
            return arr.length;
        }
        // for all i, j : i < j arr[i] >= arr[j] && x >= arr[arr.length - 1]
        // arr.length > 0
        // for all i, j : i < j arr[i] >= arr[j] && x >= arr[arr.length - 1]
        // l < r
        if (l + 1 >= r) {
            // l == r - 1
            // (arr[l] > x or not exist) && x >= arr[r]
            return r;
        }
        // l + 1 < r
        int m = (l + r) / 2;
        // l < m < r && arr[l] >= arr[m] >= arr[r]
        if (arr[m] > x) {
            //arr[m] > x && arr[l] >= arr[m] > arr[x]
            l = m;
            //arr[l] > x
        } else {
            //arr[m] <= x && arr[m] <= arr[r]
            r = m;
            //arr[r] <= x
        }
        //(arr[l] > x or not exist) && (x >= arr[r]) && (r - l) < (r' - l')

        return RecursiveSearch(x, arr, l, r);
    }

    public static void main(String[] args) {
        int x = Integer.parseInt(args[0]);
        int[] arr = new int[args.length - 1];
        for (int i = 1; i < args.length; i++) {
            arr[i - 1] = Integer.parseInt(args[i]);
        }
        int ans1 = RecursiveSearch(x, arr, -1, arr.length);
            int ans2 = IterativeSearch(x, arr);
        if (ans1 != ans2) {
            System.out.println("Mistake in search");
        }
        System.out.println(ans2);
    }
}

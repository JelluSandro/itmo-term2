package search;

public class BinarySearchMax {
    // Immutable: Exist k in [0, arr.length] : for all i in [0, arr.length - 1] i < k a[i - 1] < a[i]
    // && for all  i in [0, arr.length - 1] i > k a[i] > a[i + 1]
    // Sorted : n == n', forall i arr[i] ==arr'[i]

    // Pre: Immutable, arr.length > 0, arr not empty, arr not null, arr element all integers
    // x in [0, arr.length] : for all i in [0, arr.length - 1] arr[x] >= arr[i]

    // Post: x, arr == arr', Sorted
    static int IterativeSearch(int[] arr) {
        // Immutable
        int l = -1, r = arr.length - 1;

        // Pred: l <  r, Immutable
        // x in (l, r] && (exist x : arr[x] > arr[l] && arr[x] >= arr[r])
        // && if argument exist : arr[l - 1] < arr[l] && arr[r] > arr[r + 1]

        // Post: r - l == 1
        // R = r;

        //INV:
        // x in (l, r] && (exist x : arr[x] > arr[l] && arr[x] >= arr[r])
        // && if argument exist : arr[l - 1] < arr[l] && arr[r] > arr[r + 1]
        // -1 <= l < r < length
        // Sorted
        // R = r;

        while (l + 1 < r) {

            // x in (l, r] && (exist x : arr[x] > arr[l] && arr[x] >= arr[r])
            // && if argument exist : arr[l - 1] < arr[l] && arr[r] > arr[r + 1]
            // && l + 1 < r
            int m = l + (r - l) / 2;
            // arr[m] > arr[l] or arr[m] >= arr[r]

            // Pred: l < m && m + 1 <= r, Immutable
            // arr[m] >= arr[m + 1] or arr[m] <= arr[m + 1]
            // x in (l, r] && (exist x : arr[x] > arr[l] && arr[x] >= arr[r])
            // && if argument exist : arr[l - 1] < arr[l] && arr[r] > arr[r + 1]

            // Post: Sorted, Immutable
            // r - l' > r - l
            // l < r
            // x in (l, r] && (exist x : arr[x] > arr[l] && arr[x] >= arr[r])
            // && if argument exist : arr[l - 1] < arr[l] && arr[r] > arr[r + 1]

            if (arr[m] >= arr[m + 1]) {
                // l < m < r
                // arr[m] >= arr[m + 1] && arr[m] >= arr[r] -> [m + 1, r] no x
                // x in (l, m]
                // arr[m + 1] >= arr[r]
                r = m;
                // arr[m] >= arr[r]
                // r' - l  >  r - l
                // l < r
                // x in (l, r] && (exist x : arr[x] > arr[l] && arr[x] >= arr[r])
                // arr[m] >= arr[m + 1]
                // r = m;
                // arr[r] >= arr[r + 1]
                // l = l'
            } else {
                // l < m < r
                // arr[m] < arr[m + 1] -> [0, m] no x
                // x in [m + 1, r]
                // arr[l] < arr[m] < arr[m + 1]
                l = m;
                // arr[l] < arr[m + 1]
                // r - l' > r - l
                // l < r
                // x in (l, r] && (exist x : arr[x] > arr[l] && arr[x] >= arr[r])
                // arr[m] < arr[m + 1]
                // l = m
                // arr[l - 1] < arr[l]
                // r = r'
            }
            // r - l' > r - l
            // l < r
            // x in (l, r] && (exist x : arr[x] > arr[l] && arr[x] >= arr[r])
            // && if argument exist : arr[l - 1] < arr[l] && arr[r] > arr[r + 1]
        }
        // l < r
        // x in (l, r] && (exist x : arr[x] > arr[l] && arr[x] >= arr[r])
        // && if argument exist : arr[l - 1] < arr[l] && arr[r] > arr[r + 1]
        // l + 1 = r
        // x in (l, r], x in (l, l + 1], x = l + 1 = r
        return arr[r];
    }

    // Pre: Immutable, arr.length > 0, arr not empty, arr not null, arr element all integers
    // l, r not empty, integers, not null
    // x in (l, r]
    // x in [0, arr.length] : for all i in [0, arr.length - 1] arr[x] >= arr[i]

    // Post: x, arr == arr', Sorted
    // r - l' > r - l
    // l < r
    // x in (l, r] && (exist x : arr[x] > arr[l] && arr[x] >= arr[r])
    // && if argument exist : arr[l - 1] < arr[l] && arr[r] > arr[r + 1]
    
    static int RecursiveSearch(int[] arr, int l, int r) {
        // Immutable, Pre

        // Pred: l < r, Immutable

        // Post: l + 1 < r, Sorted

        if (l + 1 == r) {
            // l < r
            // x in (l, r] && (exist x : arr[x] > arr[l] && arr[x] >= arr[r])
            // && if argument exist : arr[l - 1] < arr[l] && arr[r] > arr[r + 1]
            // l + 1 = r
            // x in (l, r], x in (l, l + 1], x = l + 1 = r
            return arr[r];
        }
        // l + 1 < r
        int m = l + (r - l) / 2;
        // arr[m] > arr[l] or arr[m] >= arr[r]

        // Pred: l < m && m + 1 <= r, Immutable
        // arr[m] >= arr[m + 1] or arr[m] <= arr[m + 1]
        // x in (l, r] && (exist x : arr[x] > arr[l] && arr[x] >= arr[r])
        // && if argument exist : arr[l - 1] < arr[l] && arr[r] > arr[r + 1]

        // Post: Sorted, Immutable
        // r - l' > r - l
        // l < r
        // x in (l, r] && (exist x : arr[x] > arr[l] && arr[x] >= arr[r])
        // && if argument exist : arr[l - 1] < arr[l] && arr[r] > arr[r + 1]

        if (arr[m] >= arr[m + 1]) {
            // l < m < r
            // arr[m] >= arr[m + 1] && arr[m] >= arr[r] -> [m + 1, r] no x
            // x in (l, m]
            // arr[m + 1] >= arr[r]
            r = m;
            // arr[m] >= arr[r]
            // r' - l  >  r - l
            // l < r
            // x in (l, r] && (exist x : arr[x] > arr[l] && arr[x] >= arr[r])
            // arr[m] >= arr[m + 1]
            // r = m;
            // arr[r] >= arr[r + 1]
            // l = l'
        } else {
            // l < m < r
            // arr[m] < arr[m + 1] -> [0, m] no x
            // x in [m + 1, r]
            // arr[l] < arr[m] < arr[m + 1]
            l = m;
            // arr[l] < arr[m + 1]
            // r - l' > r - l
            // l < r
            // x in (l, r] && (exist x : arr[x] > arr[l] && arr[x] >= arr[r])
            // arr[m] < arr[m + 1]
            // l = m
            // arr[l - 1] < arr[l]
            // r = r'
        }

        // r - l' > r - l
        // l < r
        // x in (l, r] && (exist x : arr[x] > arr[l] && arr[x] >= arr[r])
        // && if argument exist : arr[l - 1] < arr[l] && arr[r] > arr[r + 1]

        return RecursiveSearch(arr, l, r);
    }

    // Pre args not empty, not null, integers
    // args.length > 0
    // Immutable
    public static void main(String[] args) {
        if (args == null || args.length == 0) {
            System.out.println("Invalid args");
            return;
        }
        int[] arr = new int[args.length];
        for (int i = 0; i < args.length; i++) {
            arr[i] = Integer.parseInt(args[i]);
        }
        int ans1 = RecursiveSearch(arr, -1, arr.length - 1);
        int ans2 = IterativeSearch(arr);
        if (ans1 != ans2) {
            System.out.println("Mistake in search");
        }
        // ans1 == ans2
        System.out.println(ans2);
    }
}

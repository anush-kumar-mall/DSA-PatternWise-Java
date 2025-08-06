


class NumArray {
    private int[] prefix;

    public NumArray(int[] nums) {
        int sz = nums.length;
        prefix = new int[sz];

        int sum = 0;
        for (int i = 0; i < sz; i++) {
            sum += nums[i];
            prefix[i] = sum;
        }
    }

    public int sumRange(int left, int right) {
        if (left == 0) return prefix[right];
        return prefix[right] - prefix[left - 1];
    }
}

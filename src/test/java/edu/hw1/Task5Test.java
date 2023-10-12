package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task5Test {
    @Test
    void IsPalindromeDescendantExpectTrue() {
        Integer num1 = 11211230;
        Integer num2 = 13001120;
        Integer num3 = 23336014;
        Integer num4 = 11;
        Integer num5 = 1;

        assertThat(Task5.isPalindromeDescendant(num1)).isEqualTo(true);
        assertThat(Task5.isPalindromeDescendant(num2)).isEqualTo(true);
        assertThat(Task5.isPalindromeDescendant(num3)).isEqualTo(true);
        assertThat(Task5.isPalindromeDescendant(num4)).isEqualTo(true);
        assertThat(Task5.isPalindromeDescendant(num5)).isEqualTo(true);
    }

    @Test
    void IsPalindromeDescendantExpectFalse() {
        Integer num1 = 125;
        Integer num2 = 1002;
        Integer num3 = 10009;
        Integer num4 = 3334;

        assertThat(Task5.isPalindromeDescendant(num1)).isEqualTo(false);
        assertThat(Task5.isPalindromeDescendant(num2)).isEqualTo(false);
        assertThat(Task5.isPalindromeDescendant(num3)).isEqualTo(false);
        assertThat(Task5.isPalindromeDescendant(num4)).isEqualTo(false);

    }

    @Test
    void checkIsPalindromeMethod() {
        String palindrome1 = "123321";
        String palindrome2 = "aba";
        String palindrome3 = "A";

        String notPalindrome1 = "123456";
        String notPalindrome2 = "aab";
        String notPalindrome3 = "35";

        assertThat(Task5.isPalindrome(palindrome1)).isEqualTo(true);
        assertThat(Task5.isPalindrome(palindrome2)).isEqualTo(true);
        assertThat(Task5.isPalindrome(palindrome3)).isEqualTo(true);

        assertThat(Task5.isPalindrome(notPalindrome1)).isEqualTo(false);
        assertThat(Task5.isPalindrome(notPalindrome2)).isEqualTo(false);
        assertThat(Task5.isPalindrome(notPalindrome3)).isEqualTo(false);
    }

}

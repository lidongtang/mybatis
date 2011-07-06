package org.apache.ibatis.cache;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Date;

public class CacheKeyTest {

  @Test
  public void shouldTestCacheKeysEqual() {
    Date date = new Date();
    CacheKey key1 = new CacheKey(new Object[]{1, "hello", null, new Date(date.getTime())});
    CacheKey key2 = new CacheKey(new Object[]{1, "hello", null, new Date(date.getTime())});
    assertTrue(key1.equals(key2));
    assertTrue(key2.equals(key1));
    assertTrue(key1.hashCode() == key2.hashCode());
    assertTrue(key1.toString().equals(key2.toString()));
  }

  @Test
  public void shouldTestCacheKeysNotEqualDueToDateDifference() throws Exception {
    CacheKey key1 = new CacheKey(new Object[]{1, "hello", null, new Date()});
    Thread.sleep(1000);
    CacheKey key2 = new CacheKey(new Object[]{1, "hello", null, new Date()});
    assertFalse(key1.equals(key2));
    assertFalse(key2.equals(key1));
    assertFalse(key1.hashCode() == key2.hashCode());
    assertFalse(key1.toString().equals(key2.toString()));
  }

  @Test
  public void shouldTestCacheKeysNotEqualDueToOrder() throws Exception {
    CacheKey key1 = new CacheKey(new Object[]{1, "hello", null});
    Thread.sleep(1000);
    CacheKey key2 = new CacheKey(new Object[]{1, null, "hello"});
    assertFalse(key1.equals(key2));
    assertFalse(key2.equals(key1));
    assertFalse(key1.hashCode() == key2.hashCode());
    assertFalse(key1.toString().equals(key2.toString()));
  }

  @Test
  public void shouldDemonstrateEmptyAndNullKeysAreEqual() {
    CacheKey key1 = new CacheKey();
    CacheKey key2 = new CacheKey();
    assertEquals(key1, key2);
    assertEquals(key2, key1);
    key1.update(null);
    key2.update(null);
    assertEquals(key1, key2);
    assertEquals(key2, key1);
    key1.update(null);
    key2.update(null);
    assertEquals(key1, key2);
    assertEquals(key2, key1);
  }

}

/*
 * Copyright (c) 2019 the original author or authors.
 * Licensed under the Eclipse Public License 2.0
 * which is available at http://www.eclipse.org/legal/epl-2.0
 */

package com.jiliguala.ip2region;


import java.nio.charset.StandardCharsets;
import org.lionsoul.ip2region.DataBlock;
import org.lionsoul.ip2region.IndexBlock;
import org.lionsoul.ip2region.Util;

/**
 * Based on ip2region's DbSearcher(a8f77a9).
 *
 * Changes:
 *
 * - 只保留 memorySearch
 * - 在 construct 中完成初始化 https://github.com/lionsoul2014/ip2region/issues/43
 */
public class IpSearcher {

  /**
   * for memory mode
   * the original db binary string
   */
  private final byte[] dbBinStr;

  /**
   * super blocks info
   */
  private final long firstIndexPtr;
  private final long lastIndexPtr;
  private final int totalIndexBlocks;

  private static final int BLEN = IndexBlock.getIndexBlockLength();

  /**
   * construct class
   *
   * @param dbBin db file bytes
   */
  public IpSearcher(byte[] dbBin) {
    dbBinStr = dbBin;
    //initialize the global vars
    firstIndexPtr    = Util.getIntLong(dbBinStr, 0);
    lastIndexPtr     = Util.getIntLong(dbBinStr, 4);
    totalIndexBlocks = (int)((lastIndexPtr - firstIndexPtr) / BLEN) + 1;
  }

  /**
   * get the region with a int ip address with memory binary search algorithm
   *
   * @param ip long ip
   */
  public DataBlock memorySearch(long ip) {
    //search the index blocks to define the data
    int l = 0, h = totalIndexBlocks;
    long sip, eip, dataptr = 0;
    while ( l <= h ) {
      int m = (l + h) >> 1;
      int p = (int)(firstIndexPtr + m * BLEN);

      sip = Util.getIntLong(dbBinStr, p);
      if ( ip < sip ) {
        h = m - 1;
      } else {
        eip = Util.getIntLong(dbBinStr, p + 4);
        if ( ip > eip ) {
          l = m + 1;
        } else {
          dataptr = Util.getIntLong(dbBinStr, p + 8);
          break;
        }
      }
    }

    //not matched
    if (dataptr == 0) return null;

    //get the data
    int dataLen = (int)((dataptr >> 24) & 0xFF);
    int dataPtr = (int)((dataptr & 0x00FFFFFF));
    int city_id = (int)Util.getIntLong(dbBinStr, dataPtr);
    String region = new String(dbBinStr, dataPtr + 4, dataLen - 4, StandardCharsets.UTF_8);

    return new DataBlock(city_id, region, dataPtr);
  }

  /**
   * get the region throught the ip address with memory binary search algorithm
   *
   * @param ip string ip
   * @return DataBlock
   */
  public DataBlock memorySearch(String ip) {
    return memorySearch(Util.ip2long(ip));
  }
}

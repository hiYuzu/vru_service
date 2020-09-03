package com.tcb.vru_service.service;

public interface IHistoryService {
    Object historyHeadInit();

    Object historyQuery(String[] deviceIds, String[] thingCodes, Integer dataType, String[] time);

}

package com.example.lavrastore.dao.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.lavrastore.domain.Sequence;

@Mapper
public interface SequenceMapper {
  Sequence getSequence(Sequence sequence);
  Sequence getOracleSequence(Sequence sequence);
  void updateSequence(Sequence sequence);
}